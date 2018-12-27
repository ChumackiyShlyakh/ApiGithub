package com.gd.oshturniev.apigithub.net;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.room.LiveDataCallAdapterFactory;
import com.gd.oshturniev.apigithub.utils.ApiConstants;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.media.CamcorderProfile.get;

public class RestClient {

    private static final String LOG_TAG = RestClient.class.getName();
    private static final String SSL = "TLSv1.2";
    private static final String AUTORIZATION = "Authorization";
    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;
    public Retrofit retrofit;
    private ApiGit apiGit;

    public RestClient() {
        initRestClient();
        apiGit = retrofit.create(ApiGit.class);
    }

    public ApiGit getApiGit() {
        return apiGit;
    }

    private void initRestClient() {
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_GITHUB_URL)
                    .client(createOkHttpBuilder().build())
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient.Builder createOkHttpBuilder() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(createSSLSocketFactory(), createX509TrustManager());
        builder.addInterceptor(createAuthInterceptor());
        builder.addInterceptor(createLoggedInterceptor());
        return builder;
    }

    private SSLSocketFactory createSSLSocketFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContext sslcontext = SSLContext.getInstance(SSL);
        sslcontext.init(null, new TrustManager[]{createX509TrustManager()}, null);
        SSLSocketFactory noSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());

        return noSSLv3Factory;
    }

    private X509TrustManager createX509TrustManager() throws KeyStoreException, NoSuchAlgorithmException {
        X509TrustManager trustManager = (X509TrustManager) getTrustManagers()[0];
        return trustManager;
    }

    private TrustManager[] getTrustManagers() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException(get(R.string.unexpected_default_trust_managers) + Arrays.toString(trustManagers));
        }
        return trustManagers;
    }

    private Interceptor createAuthInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().header(AUTORIZATION,
                        Credentials.basic(ApiGitHubApplication.getSharedPrefInstance().getEmail(), ApiGitHubApplication.getSharedPrefInstance().getPassword()));
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        };
    }

    private HttpLoggingInterceptor createLoggedInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(LEVEL_LOG);
        return interceptor;
    }
}

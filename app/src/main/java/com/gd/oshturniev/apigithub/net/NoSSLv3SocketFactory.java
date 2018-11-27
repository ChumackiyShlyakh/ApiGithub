package com.gd.oshturniev.apigithub.net;
/*Copyright 2015 Bhavit Singh Sengar
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class NoSSLv3SocketFactory extends SSLSocketFactory {

    private static final String TLS = "TLS";
    private SSLSocketFactory ssf;

    public NoSSLv3SocketFactory(SSLSocketFactory socketFactory) {
        try {
            SSLContext sc = SSLContext.getInstance(TLS);
            sc.init(null, null, null);
            ssf = sc.getSocketFactory();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose)
            throws IOException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(s, host, port, autoClose);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return ssf.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return ssf.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(host, port);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(host, port);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
            throws IOException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(host, port, localHost, localPort);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress,
                               int localPort) throws IOException {
        SSLSocket ss = (SSLSocket) ssf.createSocket(address, port, localAddress, localPort);
        ss.setEnabledProtocols(ss.getSupportedProtocols());
        ss.setEnabledCipherSuites(ss.getSupportedCipherSuites());
        return ss;
    }
}
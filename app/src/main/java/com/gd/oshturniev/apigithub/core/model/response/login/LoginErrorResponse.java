package com.gd.oshturniev.apigithub.core.model.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginErrorResponse {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("documentation_url")
        @Expose
        private String documentationUrl;

    public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDocumentationUrl() {
            return documentationUrl;
        }

        public void setDocumentationUrl(String documentationUrl) {
            this.documentationUrl = documentationUrl;
        }
    }
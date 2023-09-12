package com.martrust.fxrates.config;


import feign.okhttp.OkHttpClient;

public class FeignClientConfiguration {

    public OkHttpClient client() {
        return new OkHttpClient();
    }

}

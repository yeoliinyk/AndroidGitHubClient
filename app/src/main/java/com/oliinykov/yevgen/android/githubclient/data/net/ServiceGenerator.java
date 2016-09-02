package com.oliinykov.yevgen.android.githubclient.data.net;

import android.util.Base64;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Service class that stands for creation new REST clients.
 */
public class ServiceGenerator {

    private static final String BASE_URL = "https://api.github.com";

    private static RestAdapter.Builder builder =
            new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setClient(new OkClient(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass, final String username, final String password) {
        if (username != null && password != null) {
            final String credentials = username + ":" + password;
            final String encodedCredentials = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Content-Type", "application/x-www-form-urlencoded");
                    request.addHeader("Authorization", encodedCredentials);
                }
            });
        }

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, final String token) {
        if (token != null) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", "token " + token);
                }
            });
        }

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass) {

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        });

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}

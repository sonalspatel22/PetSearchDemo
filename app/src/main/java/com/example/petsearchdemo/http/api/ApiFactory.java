package com.example.petsearchdemo.http.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    @NonNull
    private final Retrofit mRetrofit;

    public ApiFactory(@NonNull final String baseUrl) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            private static final String API_KEY_QUERY_PARAM = "api_key";

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter(API_KEY_QUERY_PARAM, "6e361fe035b1d6cef0562d0df1b188a0").build();
                return chain.proceed(request.newBuilder().url(url).build());
            }
        });
        if(Boolean.parseBoolean("true")) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClientBuilder.addInterceptor(logging);
        }

        mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())).addConverterFactory(GsonConverterFactory.create()).client(okHttpClientBuilder.build()).build();
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}

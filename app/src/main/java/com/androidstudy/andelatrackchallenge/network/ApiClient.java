package com.androidstudy.andelatrackchallenge.network;

import com.google.android.gms.common.api.Api;
import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by anonymous on 10/10/17.
 */

public class ApiClient {
    /**
     * This class will help us create a re-usable Retrofit Client,
     * to avoid repeating our code!
     */

    private static Retrofit retrofit;
    private static OkHttpClient client;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLs.MIN_API_BASE_URL)
                    .client(getOkClient())
                    .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                    .build();
        }
        return retrofit;
    }

    public static Api getApi() {
        return getClient().create(Api.class);
    }

    public static Moshi getMoshi() {
        return new Moshi.Builder()
                .build();
    }

    public static OkHttpClient getOkClient() {
        if (client == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
        }
        return client;
    }
}

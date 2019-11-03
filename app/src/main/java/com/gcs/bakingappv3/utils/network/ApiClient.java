package com.gcs.bakingappv3.utils.network;

import retrofit2.Retrofit;

public class ApiClient {

    private static ApiInterface api;

    public static ApiInterface getApiClient(){
        Retrofit retrofit = RetrofitClient.getInstance();
        api = retrofit.create(ApiInterface.class);
        return api;
    }

}

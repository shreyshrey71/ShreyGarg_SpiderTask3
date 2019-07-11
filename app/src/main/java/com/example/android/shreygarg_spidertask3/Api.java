package com.example.android.shreygarg_spidertask3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Api {

    @GET("{id}?fields=etymologies&strictMatch=false")
    Call<Words> getResult(@Path("id") String ID, @Header("app_id") String appID, @Header("app_key") String appKey);
}

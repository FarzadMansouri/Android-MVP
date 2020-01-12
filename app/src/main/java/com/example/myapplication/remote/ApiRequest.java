package com.example.myapplication.remote;

import com.example.myapplication.model.Credits;
import com.example.myapplication.model.ResCat;
import com.example.myapplication.model.ResMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {


    @GET("discover/movie")
    Call<ResMovies> getcat(@Query("api_key") String Api_Key);
    @GET("movie/{movie_id}")
    Call<ResCat> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey
            , @Query("append_to_response") String credits);

}

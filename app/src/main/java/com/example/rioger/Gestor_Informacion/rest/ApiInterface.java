package com.example.rioger.Gestor_Informacion.rest;

/**
 * Created by Rioger on 05/11/2016.
 */

import com.example.rioger.Gestor_Informacion.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}

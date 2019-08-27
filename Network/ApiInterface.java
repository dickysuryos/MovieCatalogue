package com.example.dickysuryo.moviecatalogue.Network;

import com.example.dickysuryo.moviecatalogue.Model.DetailPopular_Model;
import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;
import com.example.dickysuryo.moviecatalogue.Model.MoviePopular_Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/movie?sort_by=popularity.desc")
    Call<MoviePopular_Model> getAllPopularMovie(@Query("")String Api);

    @GET("discover/movie?with_genres=18&primary_release_year=2014")
    Call<MovieNewest_Model> getAllNewestMovie(@Query("")String Api);

}

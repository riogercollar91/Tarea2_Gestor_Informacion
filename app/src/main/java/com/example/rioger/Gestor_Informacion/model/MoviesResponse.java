package com.example.rioger.Gestor_Informacion.model;

/**
 * Created by Rioger on 05/11/2016.
 */

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class MoviesResponse {

    @SerializedName("results")
    private List<Movie> results;


    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

}

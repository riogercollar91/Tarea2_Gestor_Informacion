package com.example.rioger.Gestor_Informacion.activity;

/**
 * Created by Rioger on 05/11/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rioger.Gestor_Informacion.R;
import com.example.rioger.Gestor_Informacion.adapter.MoviesAdapter;
import com.example.rioger.Gestor_Informacion.model.Movie;
import com.example.rioger.Gestor_Informacion.model.MoviesResponse;
import com.example.rioger.Gestor_Informacion.model.StoreMovie;
import com.example.rioger.Gestor_Informacion.rest.ApiClient;
import com.example.rioger.Gestor_Informacion.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "FEM";

    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";
    private StoreMovie db;
    Button button;
    List<Movie> movies;
    long numrows;
    long numrowsinsert;
    RecyclerView recyclerView;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movies = response.body().getResults();

                db = new StoreMovie(getApplicationContext(), 1);
                db.deleteAllMovies();
                long numrows = db.count();
                Log.d(LOG_TAG, "BD created, number of existing rows:  " + Long.toString(numrows));

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });

    }

    void saveMovies(View v) {

        if (flag == false) {
            Toast.makeText(
                    this,
                    getString(R.string.errorSaveBD),
                    Toast.LENGTH_SHORT
            ).show();
        } else if (flag == true) {
            boolean flag = true;
            numrowsinsert = 0;
            for (int i = 0; i < movies.size(); i++) {
                String date = movies.get(i).getReleaseDate();
                String year = date.charAt(0) + "" + date.charAt(1) + "" + date.charAt(2) + "" + date.charAt(3) + "";
                int dateInt = Integer.parseInt(year);

                if (dateInt >= 1994 && !db.exitsMovie(movies.get(i).getTitle())) {
                    db.insertMovie(movies.get(i).getTitle(), movies.get(i).getOverview(), movies.get(i).getReleaseDate(), movies.get(i).getVoteAverage());
                    Log.d(LOG_TAG, "Inserted movie:  " + movies.get(i).getTitle() + "  " + movies.get(i).getVoteAverage());
                    numrows = db.count();
                    numrowsinsert++;
                } else if (dateInt >= 1994 && db.exitsMovie(movies.get(i).getTitle())) {
                    flag = false;
                }
            }
            if (flag) {
                Log.d(LOG_TAG, "Number of rows inserted:    " + Long.toString(numrowsinsert));
                Toast.makeText(
                        this,
                        Long.toString(numrowsinsert) + " " + getString(R.string.insertedData),
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                numrows = db.count();
                Log.d(LOG_TAG, "These movies already exist");
                Log.d(LOG_TAG, "Number of existing rows:    " + Long.toString(numrows));
                Toast.makeText(
                        this,
                        getString(R.string.dataNotInserted),
                        Toast.LENGTH_SHORT
                ).show();
            }
        }


    }


    void saveMoviesApi(View v) {

        for (int i = 0; i < movies.size(); i++) {
            Log.e("Title", movies.get(i).getTitle());
            Log.e("ReleaseDate", movies.get(i).getReleaseDate());
            Log.e("Overview", movies.get(i).getOverview());

            Log.e("Id", movies.get(i).getId() + "");
            Log.e("VoteAverage", movies.get(i).getVoteAverage() + "");
            Log.e("Separator", "--------------------");
        }

        Log.e("Separator", "--------------------");
        Log.e("Movies extracted ", movies.size() + "");

        recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));

        Toast.makeText(
                this,
                getString(R.string.obtainedDataApi),
                Toast.LENGTH_SHORT
        ).show();
        flag = true;
    }

}

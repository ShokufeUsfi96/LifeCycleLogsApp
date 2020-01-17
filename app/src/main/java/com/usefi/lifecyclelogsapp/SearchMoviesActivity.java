package com.usefi.lifecyclelogsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.usefi.lifecyclelogsapp.movie.SearchMoviesClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SearchMoviesActivity extends AppCompatActivity implements MovieRecyclerAdapter.AdapterItemClicked {
    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    EditText edtSearch;
    Button btnSearch;
    RecyclerView recycler;
    MovieRecyclerAdapter movieAdapter;


    final String api_key = "df81c065bc532c4094165e8a52c94b14";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);

        btnSearch = findViewById(R.id.btnSearch);
        edtSearch = findViewById(R.id.edtSearch);
        recycler = findViewById(R.id.recycler);
        movieAdapter = new MovieRecyclerAdapter();
        recycler.setAdapter(movieAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(SearchMoviesActivity.this,RecyclerView.VERTICAL,false));
        movieAdapter.setAdapterItemClicked(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = edtSearch.getText().toString();
                AsyncHttpClient client = new AsyncHttpClient();
                String url = "http://api.themoviedb.org/3/search/movie?api_key=" + api_key + "&query=" + query;

                client.get(url, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        Gson gson = new Gson();
                        SearchMoviesClass searchMovieResult = gson.fromJson(response.toString(),SearchMoviesClass.class);
                        movieAdapter.setResults(searchMovieResult.getResults());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
                    }
                });

            }

            public  void itemClicked(int id){
                Intent intent = new Intent(SearchMoviesActivity.this, DetailMoviesActivity.class);
                intent.putExtra("itemId", id);
                startActivity(intent);

            }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

















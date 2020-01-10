package com.usefi.lifecyclelogsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class SearchMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);

        Button btnSearch = findViewById(R.id.btnSearch);
        final EditText edtSearch = findViewById(R.id.edtSearch);
        final String api_key = "df81c065bc532c4094165e8a52c94b14";
        final List<String> titles = new ArrayList<>();
        final List<String> dates = new ArrayList<>();
        final List<String> rates = new ArrayList<>();
        final List<String> images = new ArrayList<>();
        final List<Integer> ids = new ArrayList<>();

        final RecyclerView recycler = findViewById(R.id.recycler);

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


                         try {
                           JSONArray jsonArray = response.getJSONArray("results");

                           for ( int i = 0 ; i< jsonArray.length() ; i++){
                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                               String resTitle = jsonObject.getString("title");
                               String resDate = jsonObject.getString("release_date");
                               String resRate = jsonObject.getString("vote_average");
                               String resImage = jsonObject.getString("poster_path");
                               Integer resIDs = jsonObject.getInt("id");
                               titles.add(resTitle);
                               dates.add(resDate);
                               rates.add(resRate);
                               images.add(resImage);
                               ids.add(resIDs);
                           }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });

                MovieRecyclerAdapter movieAdapter = new MovieRecyclerAdapter(titles, dates, rates, images, ids, getApplicationContext());
                recycler.setAdapter(movieAdapter);
                recycler.setLayoutManager(new LinearLayoutManager(SearchMoviesActivity.this, RecyclerView.VERTICAL,false));


                    }
                });


            }
        }

















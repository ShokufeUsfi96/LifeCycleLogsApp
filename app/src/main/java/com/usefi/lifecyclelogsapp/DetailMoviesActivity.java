package com.usefi.lifecyclelogsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.usefi.lifecyclelogsapp.movie.DetailMoviesClass;

import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.SSLContext;

import cz.msebera.android.httpclient.Header;

public class DetailMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        final ImageView imgDetail = findViewById(R.id.imgDetail);
        final TextView title = findViewById(R.id.titleDetail);
        final TextView rate = findViewById(R.id.rateDetail);
        final TextView date = findViewById(R.id.dateDetail);
        TextView overview = findViewById(R.id.overview);
        final String api_key = "df81c065bc532c4094165e8a52c94b14";

        Intent intent = getIntent();
        List<Integer> ids = intent.getIntegerArrayListExtra("IDs");
        Integer resultPos = intent.getIntExtra("position", 0);

        int movie_id = ids.get(resultPos);
        AsyncHttpClient client = new AsyncHttpClient();
        String address = "https://api.themoviedb.org/3/movie/" + movie_id + "?api_key=" + api_key;

        client.get(address, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Gson gson = new Gson();
                DetailMoviesClass detail = gson.fromJson(response.toString(),DetailMoviesClass.class);

                title.setText(detail.getTitle());
                date.setText(detail.getReleaseDate());
                rate.setText(detail.getVoteAverage().toString());
                String imgAddress = "http://image.tmdb.org/t/p/w45" + detail.getPosterPath();
                Glide.with(imgDetail.getContext()).load(imgAddress).into(imgDetail);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });




    }
}

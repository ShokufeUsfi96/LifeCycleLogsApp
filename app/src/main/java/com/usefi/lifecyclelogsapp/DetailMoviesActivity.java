package com.usefi.lifecyclelogsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

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

import static android.os.Build.ID;
import static com.usefi.lifecyclelogsapp.R.drawable.ic_bookmark_black;
import static com.usefi.lifecyclelogsapp.R.drawable.ic_bookmark_border;

public class DetailMoviesActivity extends AppCompatActivity {


    ImageView imgPoster;
    TextView title, date, rate, overview;
    Button btnSave;
    Intent intent;
    int ID;
    SqLiteDbHelper dbHelper;
    final String api_key = "df81c065bc532c4094165e8a52c94b14";
    DetailMoviesClass detail;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        intent = getIntent();
        imgPoster = findViewById(R.id.imgPoster);
        title = findViewById(R.id.title);
        rate = findViewById(R.id.rate);
        date = findViewById(R.id.date);
        overview = findViewById(R.id.overview);
        btnSave = findViewById(R.id.btnSave);
        dbHelper = new SqLiteDbHelper(this, "MovieDetail", null, 1);
        rb = findViewById(R.id.rb);



        if (intent.getIntExtra("itemId", 0) != 0) {
            ID = intent.getIntExtra("itemId", 0);

            if (dbHelper.GetMovieInfo(ID))
                btnSave.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0, R.drawable.ic_bookmark_black);

            AsyncHttpClient client = new AsyncHttpClient();
            String address = "http://api.themoviedb.org/3/movie/" + ID + "?api_key=" + api_key;

            client.get(address, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    Gson gson = new Gson();
                    detail = gson.fromJson(response.toString(), DetailMoviesClass.class);

                    Glide.with(imgPoster).load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + detail.getPosterPath()).into(imgPoster);
                    title.setText(detail.getTitle());
                    date.setText(detail.getReleaseDate());
                    rate.setText(detail.getVoteAverage().toString());
                    overview.setText(detail.getOverview());
                    rb.setRating((detail.getVoteAverage()/2));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (!dbHelper.GetMovieInfo(detail.getId())){
                    dbHelper.InsertToDB(detail.getId());
                    Log.i("DBTAG", "data inseted.");
                   btnSave.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_bookmark_black, 0, 0, 0);
                    Log.i("DBTAG", "bg is black.");
                }
                else{
                    dbHelper.DeleteFromDB(detail.getId());
                   Log.i("DBTAG", "data deleted.");
                   btnSave.setCompoundDrawablesWithIntrinsicBounds( ic_bookmark_border, 0, 0, 0);
                    Log.i("DBTAG", "bg is border.");
                }

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new  Intent(this,SearchMoviesActivity.class));
                this.finish();
                return  true;
            default:
                    return super.onOptionsItemSelected(item);
        }
    }
}

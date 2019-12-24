package com.usefi.lifecyclelogsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.usefi.lifecyclelogsapp.PrayTimesClass;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class AladhanTimes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aladhan_times);

        final Button btnTimes = findViewById(R.id.btnTimes);

        final TextView txtFajr = findViewById(R.id.txtFajr);
        final TextView txtSunrise = findViewById(R.id.txtSunrise);
        final TextView txtDhuhr = findViewById(R.id.txtDhuhr);
        final TextView txtAsr = findViewById(R.id.txtAsr);
        final TextView txtSunset = findViewById(R.id.txtSunset);
        final TextView txtMaghrib = findViewById(R.id.txtMaghrib);
        final TextView txtImsak = findViewById(R.id.txtIsha);
        final TextView txtIsha = findViewById(R.id.txtImsak);
        final TextView txtMidnight = findViewById(R.id.txtMidnight);

        final EditText edtCity = findViewById(R.id.edtCity);


        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edtCity.getText().toString();

                AsyncHttpClient client = new AsyncHttpClient();
                String address = "https://api.aladhan.com/v1/timingsByCity?city=" + city + "&country=Iran&method=8";
                client.get(address,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);


                        Gson gson = new Gson();
                        PrayTimesClass pray = gson.fromJson(response.toString(), PrayTimesClass.class);

                        txtFajr.setText(pray.getData().getTimings().getFajr());
                        txtSunrise.setText(pray.getData().getTimings().getSunrise());
                        txtDhuhr.setText(pray.getData().getTimings().getDhuhr());
                        txtAsr.setText(pray.getData().getTimings().getAsr());
                        txtSunset.setText(pray.getData().getTimings().getSunset());
                        txtMaghrib.setText(pray.getData().getTimings().getMaghrib());
                        txtIsha.setText(pray.getData().getTimings().getIsha());
                        txtImsak.setText(pray.getData().getTimings().getImsak());
                        txtMidnight.setText(pray.getData().getTimings().getMidnight());

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
            }
        });



    }
}

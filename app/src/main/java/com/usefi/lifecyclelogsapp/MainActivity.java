package com.usefi.lifecyclelogsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    ArrayList<String> arrayList;
    TextView txtItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        arrayList.add("Profile");
        arrayList.add("Dial");

        Button btnDrawer = findViewById(R.id.btnDrawer);
        drawerLayout = findViewById(R.id.drawerLayout);
        RecyclerView recycler = findViewById(R.id.recycler);
        TestRecyclerAdapter adapter = new TestRecyclerAdapter(arrayList);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        txtItem = findViewById(R.id.txtItem);

        btnDrawer.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnDrawer) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }

    }



}

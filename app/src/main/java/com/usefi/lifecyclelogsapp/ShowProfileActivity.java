package com.usefi.lifecyclelogsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtFamily = findViewById(R.id.txtFamily);
        TextView txtAge = findViewById(R.id.txtAge);
        TextView txtAddress = findViewById(R.id.txtAddress);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnEdit = findViewById(R.id.btnEdit);

        Intent intent = getIntent();
        String name = intent.getStringExtra("personName");
        txtName.setText(name);
        String family = intent.getStringExtra("personFamily");
        txtFamily.setText(family);
        String age = intent.getStringExtra("personAge");
        txtAge.setText(age);
        String address = intent.getStringExtra("personAddress");
        txtAddress.setText(address);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(Activity.RESULT_OK, intent1);
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToEdit = new Intent(ShowProfileActivity.this, EditProfileActivity.class);
                startActivity(intentToEdit);
            }
        });

    }
}

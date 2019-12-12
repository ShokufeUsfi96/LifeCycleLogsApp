package com.usefi.lifecyclelogsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {
EditText edtName, edtFamily, edtAge, edtAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edtName = findViewById(R.id.edtName);
        edtFamily = findViewById(R.id.edtFamily);
        edtAge = findViewById(R.id.edtAge);
        edtAddress = findViewById(R.id.edtAddress);
        Button btnSave = findViewById(R.id.btnSave);

        final String name = PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this).getString("personName","");
        edtName.setText(name);
        final String family = PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this).getString("personFamily","");
        edtFamily.setText(family);
        final String age = PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this).getString("personAge","");
        edtAge.setText(age);
        final String address = PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this).getString("personAddress","");
        edtAddress.setText(address);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EditProfileActivity.this,ShowProfileActivity.class);
                intent.putExtra("personName", edtName.getText().toString());
                intent.putExtra("personFamily", edtFamily.getText().toString());
                intent.putExtra("personAge", edtAge.getText().toString());
                intent.putExtra("personAddress", edtAddress.getText().toString());
                startActivityForResult(intent,111);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 111){
            if (resultCode == Activity.RESULT_OK){
                String name = edtName.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this).edit().putString("personName", name).apply();
                String family = edtFamily.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this).edit().putString("personFamily", family).apply();
                String age = edtAge.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this).edit().putString("personAge", age).apply();
                String address = edtAddress.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this).edit().putString("personAddress", address).apply();

            }
        }
    }
}

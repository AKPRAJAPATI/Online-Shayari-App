package com.example.socialmediashayariapp.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.socialmediashayariapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(getResources().getColor(R.color.gulabi));

    }

    public void GOTO_REGISTER_ACTIVITY(View view) {
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
    }
}
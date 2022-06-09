package com.example.socialmediashayariapp.authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialmediashayariapp.R;
import com.example.socialmediashayariapp.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(getResources().getColor(R.color.gulabi));
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Regisration");
        auth = FirebaseAuth.getInstance();


        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.editEmail.getText().toString().isEmpty()) {
                    setToast(getApplicationContext(), "Email is empty");
                } else if (!binding.editEmail.getText().toString().contains("@gmail.com")) {
                    setToast(getApplicationContext(), "Email is wrong");
                } else if (binding.editPassword.getText().toString().isEmpty()) {
                    setToast(getApplicationContext(), "Password is empty");
                } else if (binding.editPassword.getText().toString().length() < 8) {
                    setToast(getApplicationContext(), "Password more than 8 character");
                } else if (!binding.editPassword.getText().toString().equals(binding.editConfirmPassword.getText().toString())) {
                    setToast(getApplicationContext(), "Password is not match");
                } else {
                    progressDialog.show();
                    registerAccount();
                }
            }
        });
    }

    private void registerAccount() {
        auth.createUserWithEmailAndPassword(binding.editEmail.getText().toString(), binding.editPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                    Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), SetupActivity.class));

            }
        });
    }

    private void setToast(Context applicationContext, String email_is_empty) {
        Toast.makeText(applicationContext, "" + email_is_empty, Toast.LENGTH_SHORT).show();
    }

    public void GOTO_LOGIN_ACTIVITY(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }
}
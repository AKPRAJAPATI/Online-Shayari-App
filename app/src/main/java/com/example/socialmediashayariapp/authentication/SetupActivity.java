package com.example.socialmediashayariapp.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialmediashayariapp.MainActivity;
import com.example.socialmediashayariapp.R;
import com.example.socialmediashayariapp.databinding.ActivitySetupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class SetupActivity extends AppCompatActivity {
    ActivitySetupBinding binding;
    Uri IMAGE_URI;
    String image_uri;

    DatabaseReference databaseReference;
    FirebaseAuth auth;
    StorageReference storageReference;

    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(getResources().getColor(R.color.gulabi));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sumbit Data");
        progressDialog.setCancelable(false);


        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();


        binding.userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IMAGE_URI != null) {
                    progressDialog.show();
                    storageReference.child("Profile").child(auth.getUid()).putFile(IMAGE_URI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> imaegTask = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            imaegTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    image_uri = uri.toString();
                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    hashMap.put("profile_image", image_uri);
                                    hashMap.put("name", binding.userName.getText().toString());
                                    hashMap.put("village", binding.userVillage.getText().toString());
                                    hashMap.put("phone", binding.userPhone.getText().toString());
                                    hashMap.put("work", binding.userWork.getText().toString());
                                    hashMap.put("distic", binding.userDistic.getText().toString());


                                    databaseReference.child("Users").child(auth.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressDialog.dismiss();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            }
                                        }
                                    });

                                }
                            });
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 || resultCode == RESULT_OK) {
            IMAGE_URI = data.getData();
            binding.userProfile.setImageURI(IMAGE_URI);
        }
    }
}
package com.example.socialmediashayariapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialmediashayariapp.Models.Model_post;
import com.example.socialmediashayariapp.Models.Post;
import com.example.socialmediashayariapp.databinding.ActivityAddPostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class Add_Post_Activity extends AppCompatActivity {
    ActivityAddPostBinding binding;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    String name;
    String work;
    String profileimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        getMyData();
        binding.savePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shayari = binding.myShayariya.getText().toString();
                if (shayari.isEmpty()) {
                    Toast.makeText(Add_Post_Activity.this, "Please enter the shayari", Toast.LENGTH_SHORT).show();
                    binding.savePost.setText("Fill the Shayri");
                } else {
                    binding.savePost.setText("Save");
// Model_post post = new Model_post(profileimage,name,binding.myShayariya.getText().toString(),work);
                    Model_post post = new Model_post();
                    post.setImageUrl(profileimage);
                    post.setName(name);
                    post.setShayari(binding.myShayariya.getText().toString());
                    post.setWork(work);
                    post.setPostedBy(auth.getUid());


                    databaseReference.child("Post").push().setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                binding.myShayariya.setText("");
                                Toast.makeText(Add_Post_Activity.this, "Saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

    private void getMyData() {

        databaseReference.child("Users").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                      name = snapshot.child("name").getValue(String.class);
                      work = snapshot.child("work").getValue(String.class);
                      profileimage = snapshot.child("profile_image").getValue(String.class);
                    binding.userPostname.setText(name);
                    binding.userPostWork.setText(work);
                    Picasso.get().load(profileimage).into(binding.circleImageView);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.socialmediashayariapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediashayariapp.Adapters.CommentsAdapter;
import com.example.socialmediashayariapp.Models.commentsModel;
import com.example.socialmediashayariapp.databinding.ActivityCommentsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    ActivityCommentsBinding binding;
    String myShayari;
    String profileImage;
    String shayriKey;
    String name;

    DatabaseReference databaseReference;
    FirebaseAuth auth;
    CommentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        myShayari = getIntent().getStringExtra("shayari");
        profileImage = getIntent().getStringExtra("profile");
        name = getIntent().getStringExtra("name");
        shayriKey = getIntent().getStringExtra("key");

        binding.shayriya.setText(myShayari);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        binding.commentsRecyclerview.setLayoutManager(linearLayoutManager);

        binding.commentsRecyclerview.setHasFixedSize(true);
        ArrayList<commentsModel> arrayList = new ArrayList<>();
        adapter = new CommentsAdapter(getApplicationContext(), arrayList);
        databaseReference.child("Post").child(shayriKey).child("comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    commentsModel model = dataSnapshot.getValue(commentsModel.class);
                    arrayList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.commentsRecyclerview.setAdapter(adapter);
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.sendCommnets.getText().toString().isEmpty()) {
                    Toast.makeText(CommentsActivity.this, "Enter any comments ", Toast.LENGTH_SHORT).show();
                } else {
                    commentsModel commentsModel = new commentsModel();
                    commentsModel.setMyComments(binding.sendCommnets.getText().toString());
                    commentsModel.setImageUrl(profileImage);
                    commentsModel.setPostedBy(auth.getUid());
                    commentsModel.setName(name);
                    commentsModel.setPostId(shayriKey);
                    databaseReference.child("Post").child(shayriKey).child("comments").push().setValue(commentsModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                binding.sendCommnets.setText("");
                                Toast.makeText(CommentsActivity.this, "send comments", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
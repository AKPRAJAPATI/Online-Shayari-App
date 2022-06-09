package com.example.socialmediashayariapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediashayariapp.CommentsActivity;
import com.example.socialmediashayariapp.Models.Model_post;
import com.example.socialmediashayariapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.myviewHolder> {

    Context context;
    ArrayList<Model_post> arrayList;

    DatabaseReference databaseReference;
    FirebaseAuth auth;


    public Adapter(Context context, ArrayList<Model_post> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewHolder(LayoutInflater.from(context).inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {

        //get firebase instance
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        Model_post model_post = arrayList.get(position);
        holder.name.setText(model_post.getName());
        holder.shayari.setText(model_post.getShayari());
        holder.work.setText(model_post.getWork());
        Picasso.get().load(model_post.getImageUrl()).placeholder(R.drawable.user).into(holder.profileImage);


        //work on the buttons
        int like_position = holder.getAdapterPosition();
//        holder.like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //get value of firebase database
//                //getlikebtn
//                databaseReference.child("Post").child(model_post.getPostId()).child("likes").child(auth.getUid()).setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
//                        holder.like.setImageResource(R.drawable.heart1);
//
//                        holder.like.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                databaseReference.child("Post").child(model_post.getPostId()).child("likes").child(auth.getUid()).setValue(false).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(context, "UnLiked", Toast.LENGTH_SHORT).show();
//                                        holder.like.setImageResource(R.drawable.heart);
//
//                                    }
//                                });
//                            }
//                        });
//                    }
//                });
//            }
//        });
        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentsActivity.class);
                intent.putExtra("shayari",model_post.getShayari());
                intent.putExtra("profile",model_post.getImageUrl());
                intent.putExtra("name",model_post.getName());
                intent.putExtra("key",model_post.getPostId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plane");
                intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_TEXT,model_post.getShayari());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myviewHolder extends RecyclerView.ViewHolder {

        CircleImageView profileImage;
        TextView name;
        TextView work;
        TextView shayari;

        //work on the buttons
        ImageView like, comments, share;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.circleImageView);
            name = itemView.findViewById(R.id.userPostname);
            work = itemView.findViewById(R.id.userPostworking);
            shayari = itemView.findViewById(R.id.shayriya);

            //work on the comments
            comments = itemView.findViewById(R.id.comments);
            share = itemView.findViewById(R.id.share);


        }
    }
}

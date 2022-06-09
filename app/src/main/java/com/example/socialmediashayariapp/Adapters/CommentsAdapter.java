package com.example.socialmediashayariapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediashayariapp.Models.commentsModel;
import com.example.socialmediashayariapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.viewHolder> {

    Context context;
    ArrayList<commentsModel> arrayList;

    public CommentsAdapter(Context context, ArrayList<commentsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.items_comments, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        commentsModel model = arrayList.get(position);
        holder.name.setText(model.getName());
        holder.commnets.setText(model.getMyComments());
        Picasso.get().load(model.getImageUrl()).into(holder.profileImage);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView name, commnets;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.circleImageView);
            name = itemView.findViewById(R.id.userPostname);
            commnets = itemView.findViewById(R.id.commentText);


        }
    }
}

package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.Comment;
import com.example.duantotnghiep_md27.Model.CommentData;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.MyViewHolder> {
    List<Comment> commentList = new ArrayList<>();
    Context context;

    public Comment_Adapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Comment comment = commentList.get(position);

        holder.user_fullname.setText(comment.getFull_name());
        holder.commenttext.setText(comment.getComment_text());
        holder.commentTime.setText(comment.getComment_date());


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView commentTime, commenttext, user_fullname;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            commentTime = itemView.findViewById(R.id.commenttime);
            commenttext = itemView.findViewById(R.id.commenttext);
            user_fullname = itemView.findViewById(R.id.user_fullname);

//            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}

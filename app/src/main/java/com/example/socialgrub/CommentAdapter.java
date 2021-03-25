package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    Context context;
    ArrayList<String> commentList;
    String username;

    public CommentAdapter(Context context, ArrayList<String> commentList, String username) {

        this.context = context;
        this.commentList = commentList;
        this.username = username;


    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_comment_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {

        String by = "User: " + username;

        holder.commentTextBox.setText(commentList.get(position));
        holder.commentUsername.setText(by);



    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }




    class CommentViewHolder extends RecyclerView.ViewHolder {


        TextView commentTextBox;
        TextView commentUsername;



        public CommentViewHolder(@NonNull View itemView) {


            super(itemView);

            commentTextBox = itemView.findViewById(R.id.commentNameId);
            commentUsername = itemView.findViewById(R.id.userCommentName);
        }



    }




}
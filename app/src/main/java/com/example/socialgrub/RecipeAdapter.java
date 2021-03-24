package com.example.socialgrub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.Holder>{

    Context context;
    ArrayList<Recipe> profilePostList;

    PostAdapter postAdapter;
    ArrayList<Post> postList;

    int tempPos;

    public RecipeAdapter(Context context, ArrayList<Recipe> profilePostList) {
        this.context = context;
        this.profilePostList = profilePostList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_posts, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.postTitle.setText(profilePostList.get(position).getRecipeTitle());
        holder.tagOne.setText(profilePostList.get(position).getOneTag());
        Picasso.get().load(profilePostList.get(position).getRecipeUrl()).into(holder.postImage);
        tempPos = position;
    }

    @Override
    public int getItemCount() {
        return profilePostList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView postImage;
        TextView tagOne, postTitle;

        public Holder(@NonNull View itemView){
            super(itemView);

            postImage = itemView.findViewById(R.id.profileRecipeImage);
            postTitle = itemView.findViewById(R.id.profileRecipeTitle);
            tagOne = itemView.findViewById(R.id.profileRecipeTag1);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            intent =  new Intent(context, SettingsActivity.class);
            context.startActivity(intent);
        }
    }
}

package com.example.socialgrub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.Holder>{

    Context context;
    ArrayList<Recipe> profilePostList;
//    ArrayList<Post> postList;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int indexOfClicked = holder.getAdapterPosition();

//                String postID =  profilePostList.get(indexOfClicked).getPostID();
                Bundle bundle = new Bundle();

//                bundle.putParcelable("postID", Parcels.wrap(postID));

                Intent goToViewIngredientDirections = new Intent(context.getApplicationContext(),DisplayIngredientAndDirections.class);
                goToViewIngredientDirections.putExtras(bundle);
                goToViewIngredientDirections.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(goToViewIngredientDirections);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profilePostList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView postImage;
        TextView tagOne, postTitle;

        public Holder(@NonNull View itemView){
            super(itemView);

            postImage = itemView.findViewById(R.id.profileRecipeImage);
            postTitle = itemView.findViewById(R.id.profileRecipeTitle);
            tagOne = itemView.findViewById(R.id.profileRecipeTag1);
        }
    }
}

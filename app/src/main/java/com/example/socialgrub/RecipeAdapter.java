package com.example.socialgrub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.Holder>{

    Context context;
    List<Recipe> profilePostList;

    public RecipeAdapter(Context context, List<Recipe> profilePostList) {
        this.context = context;
        this.profilePostList = profilePostList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_profile, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String title = profilePostList.get(position).getRecipeTitle();

        ArrayList<Tag> tags = profilePostList.get(position).getRecipeTags();

        //String tag1 = profilePostList.get(position).getRecipeTagOne();
        //String tag2 = profilePostList.get(position).getRecipeTagTwo();
        //String tag3 = profilePostList.get(position).getRecipeTagThree();
        String image = profilePostList.get(position).getRecipeUrl();

        holder.postTitle.setText(title);
        holder.tagOne.setText(tags.get(0).getTagString());
        holder.tagTwo.setText(tags.get(1).getTagString());
        holder.tagThree.setText(tags.get(2).getTagString());

        try {
            Picasso.get().load(image).into(holder.postImage);
        }
        catch(Exception e) {

        }

        holder.viewPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "View Post", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return profilePostList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView postImage;
        TextView tagOne, tagTwo, tagThree, postTitle;
        Button viewPostButton;

        public Holder(@NonNull View itemView){
            super(itemView);

            postImage = itemView.findViewById(R.id.profileRecipeImage);
            tagOne = itemView.findViewById(R.id.profileRecipeTag1);
            tagTwo = itemView.findViewById(R.id.profileRecipeTag2);
            tagThree = itemView.findViewById(R.id.profileRecipeTag3);
            postTitle = itemView.findViewById(R.id.profileRecipeTitle);
            viewPostButton = itemView.findViewById(R.id.profileRecipeViewPostButton);
        }
    }
}

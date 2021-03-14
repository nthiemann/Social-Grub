package com.example.socialgrub;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialgrub.R;
import com.example.socialgrub.Recipe;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{



    Context context;
    ArrayList<Post> listOfPosts;



    public PostAdapter(Context context, ArrayList<Post> listOfPosts) {

        this.context = context;
        this.listOfPosts = listOfPosts;
    }



    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PostViewHolder(LayoutInflater.from(context).inflate(R.layout.post_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {




        holder.recipeTitle.setText(listOfPosts.get(position).getRecipeTitle());
        //holder.recipeDescription.setText(post.getDescription());
        Picasso.get().load(listOfPosts.get(position).getRecipeURL()).into(holder.imageToPost);

    }

    @Override
    public int getItemCount() {
        return listOfPosts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {


        ImageView imageToPost;
        TextView recipeTitle;
       // TextView recipeDescription;
        //TextView username;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            imageToPost = itemView.findViewById(R.id.imagePostId);
            recipeTitle = itemView.findViewById(R.id.postTitleInList);
            //recipeDescription = itemView.findViewById(R.id.postDescriptionInList);
            //username = itemView.findViewById(R.id.usernameIdInList);

        }
    }




}

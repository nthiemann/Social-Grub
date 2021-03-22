package com.example.socialgrub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialgrub.R;
import com.example.socialgrub.Recipe;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{



    Context context;
    ArrayList<Post> listOfPosts;
    String postID;



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
       // holder.recipeDescription.setText(post.getDescription());
        Picasso.get().load(listOfPosts.get(position).getRecipeURL()).into(holder.imageToPost);

        postID = listOfPosts.get(position).getPostID();

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
        Button toViewList;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            imageToPost = itemView.findViewById(R.id.imagePostId);
            recipeTitle = itemView.findViewById(R.id.postTitleInList);
            //recipeDescription = itemView.findViewById(R.id.postDescriptionInList);
            //username = itemView.findViewById(R.id.usernameIdInList);


            toViewList = itemView.findViewById(R.id.buttonToViewList);
            toViewList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    Bundle bundle = new Bundle();

                    bundle.putParcelable("postID", Parcels.wrap(postID));

                    Intent goToViewIngredientDirections = new Intent(context.getApplicationContext(),DisplayIngredientAndDirections.class);
                    goToViewIngredientDirections.putExtras(bundle);
                    goToViewIngredientDirections.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(goToViewIngredientDirections);

                }
            });

        }




    }




}

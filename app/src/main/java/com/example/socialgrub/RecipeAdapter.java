package com.example.socialgrub;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.Holder>{

    String tTitle[];
    Context context;

    public RecipeAdapter(Context ct, String thisTitle[])
    {
        context = ct;
        tTitle = thisTitle;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_test, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.Holder holder, int position) {
//        holder.nameTxt.setText(tTitle[position]);
        holder.nameTxt.setText(tTitle[0]);
    }

    @Override
    public int getItemCount() {
        return tTitle.length;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView nameTxt;

        public Holder(final View view){
            super(view);
            nameTxt = view.findViewById(R.id.testTitle);
        }
    }
//    Context context;
//    List<Recipe> profilePostList;
//
////    public RecipeAdapter(Context context, List<Recipe> profilePostList) {
////        this.context = context;
////        this.profilePostList = profilePostList;
////    }
//
//    String data1[], data2[], data3[], data4[];
//    int images[];
//
//    public RecipeAdapter(Context context, String s1[], String s2[], String s3[], String s4[], int img[]) {
//        this.context = context;
//        data1 = s1;
//        data2 = s2;
//        data3 = s3;
//        data4 = s4;
//        images = img;
//    }
//
//    @NonNull
//    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(context).inflate(R.layout.profile_posts, parent, false);
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view = layoutInflater.inflate(R.layout.profile_posts, parent, false);
//
////        LinearLayoutManager layoutManager = new LinearLayoutManager(
////                getActivity());
////        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
////        mRecyclerView.setLayoutManager(layoutManager);
//
//        return new Holder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Holder holder, int position) {
////        String title = profilePostList.get(position).getRecipeTitle();
////        String tag1 = profilePostList.get(position).getRecipeTagOne();
////        String tag2 = profilePostList.get(position).getRecipeTagTwo();
////        String tag3 = profilePostList.get(position).getRecipeTagThree();
////        String image = profilePostList.get(position).getRecipeUrl();
////
////        holder.postTitle.setText(title);
////        holder.tagOne.setText(tag1);
////        holder.tagTwo.setText(tag2);
////        holder.tagThree.setText(tag3);
////
////        try {
////            Picasso.get().load(image).into(holder.postImage);
////        }
////        catch(Exception e) {
////
////        }
////
////        holder.viewPostButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(context, "View Post", Toast.LENGTH_SHORT).show();
////            }
////        });
//
//
//        holder.postTitle.setText(data1[position]);
//        holder.tagOne.setText(data2[position]);
//        holder.tagTwo.setText(data3[position]);
//        holder.tagThree.setText(data4[position]);
//        holder.postImage.setImageResource(images[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return data1.length;
//    }
//
//    class Holder extends RecyclerView.ViewHolder{
//
//        ImageView postImage;
//        TextView tagOne, tagTwo, tagThree, postTitle;
//        Button viewPostButton;
//
//        public Holder(@NonNull View itemView){
//            super(itemView);
//
//            postImage = itemView.findViewById(R.id.profileRecipeImage);
//            tagOne = itemView.findViewById(R.id.profileRecipeTag1);
//            tagTwo = itemView.findViewById(R.id.profileRecipeTag2);
//            tagThree = itemView.findViewById(R.id.profileRecipeTag3);
//            postTitle = itemView.findViewById(R.id.profileRecipeTitle);
//            viewPostButton = itemView.findViewById(R.id.profileRecipeViewPostButton);
//        }
//    }
}

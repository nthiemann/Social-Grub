package com.example.socialgrub;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class ViewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_adapter);


    }

    private void getData() {
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("title")) {

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("title");

            setImage(imageUrl, imageName);
        }
    }

    private void setImage(String imageUrl, String imageName) {
        TextView name = findViewById(R.id.postTitleInList);
        name.setText(imageName);

        ImageView image = findViewById(R.id.imagePostId);
        Picasso.get().load(imageUrl).into(image);
    }
}

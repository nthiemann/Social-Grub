package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;
import org.w3c.dom.Comment;

import java.util.ArrayList;

public class DisplayComments extends AppCompatActivity {


    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrieveCommentsFromDatabase = db.getReference("Image Dish");

    RecyclerView recyclerListOfComments;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_comments);

        recyclerListOfComments = findViewById(R.id.recyclerListOfComments);
        backButton = findViewById(R.id.backButtonToPost);

        String postID = Parcels.unwrap(getIntent().getParcelableExtra("postID"));


        recyclerListOfComments.setLayoutManager(new LinearLayoutManager(this));

        retrieveCommentsFromDatabase.child(postID).child("Comments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<String> commentList = new ArrayList<>();
                ArrayList<String> userList = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    String commentContent = dataSnapshot1.child("Comment String").getValue().toString();
                    String username = dataSnapshot1.child("Username").getValue().toString();
                    commentList.add(commentContent);
                    userList.add(username);


                }


                CommentAdapter commentAdapter = new CommentAdapter(DisplayComments.this, commentList,userList);
                recyclerListOfComments.setAdapter(commentAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
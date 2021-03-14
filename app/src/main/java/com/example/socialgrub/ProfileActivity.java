package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;

    String title[] = {"Test1", "Test2", "Test3", "Test4", "Test5"};

//    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("-MVDXb5bcZhkXKVr7R-1");

//    RecyclerView recyclerView;
//    List<Recipe> recipeList;
//    RecipeAdapter recipeAdapter;
//
//    String s1[] = {"Title1"}, s2[] = {"Tag1"}, s3[] = {"Tag2"}, s4[] = {"Tag3"};
//    int images[] = {R.drawable.profile_image};
//    String images[] = {"https://firebasestorage.googleapis.com/v0/b/social-grub.appspot.com/o/Posts%2F1615151653991.null?alt=media&token=50794e2c-2c8b-462a-af1a-5ae19ee51e9b",
//            "https://firebasestorage.googleapis.com/v0/b/social-grub.appspot.com/o/Posts%2F1615152198286.null?alt=media&token=9405c171-64cf-4109-be45-ef4da8a11ca0",
//            "https://firebasestorage.googleapis.com/v0/b/social-grub.appspot.com/o/Posts%2F1615152307525.null?alt=media&token=9cce337b-ab19-496f-9e00-6db56016e3fb",
//            "https://firebasestorage.googleapis.com/v0/b/social-grub.appspot.com/o/Posts%2F1615152401672.null?alt=media&token=f615cd7b-1af4-4266-b367-06cffd283b79",
//            "https://firebasestorage.googleapis.com/v0/b/social-grub.appspot.com/o/Posts%2F1615155237697.null?alt=media&token=dedc749b-5852-4d00-921c-8b79b094e33e",
//            "https://firebasestorage.googleapis.com/v0/b/social-grub.appspot.com/o/Posts%2F1615155531669.null?alt=media&token=3849bec2-ad56-496c-bf84-48e5c49862a8",
//            "https://firebasestorage.googleapis.com/v0/b/social-grub.appspot.com/o/Posts%2F1615155689162.null?alt=media&token=d93bba6f-cef0-4331-b10d-0c854d91b28f"};

    Button editProfileBtn;
    ImageButton settingsProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = findViewById(R.id.profilePostView);

        editProfileBtn = (Button) findViewById(R.id.edit_profile_button);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToeditPage = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(goToeditPage);
            }
        });

        settingsProfileBtn = (ImageButton) findViewById(R.id.settings_profile_button);

        settingsProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ProfileActivity.this, SettingsActivity.class));
            }
        });

        RecipeAdapter recipeAdapter = new RecipeAdapter(this, title);
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView = findViewById(R.id.profilePostView);
////        s1 = ;
////        s2 = ;
////        s3 = ;
////        ImageView view1 = Picasso.get().load("http://i.imgur.com/DvpvklR.png");
//        RecipeAdapter recipeAdapter = new RecipeAdapter(this, s1, s2, s3, s4, images);
//        recyclerView.setAdapter(recipeAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
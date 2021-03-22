package com.example.socialgrub;

import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class ProfileActivityTest {

    @Test
    public void test_visibilityProfile() {

        ActivityScenario<ProfileActivity> activityScenario = ActivityScenario.launch(ProfileActivity.class);
        onView(withId(R.id.profileID)).check(matches(isDisplayed()));
    }

    @Test
    public void test_functionalityButtonProfile() {

        ActivityScenario<ProfileActivity> activityScenario = ActivityScenario.launch(ProfileActivity.class);
        onView(withId(R.id.edit_profile_button)).perform(click());
        onView(withId(R.id.editProfileID)).check(matches(isDisplayed()));

        pressBack();

        onView(withId(R.id.settings_profile_button)).perform(click());
        onView(withId(R.id.settingsPageID)).check(matches(isDisplayed()));

        pressBack();
        onView(withId(R.id.profileID)).check(matches(isDisplayed()));


    }

    @Test
    public void test_username(){

        ActivityScenario<ProfileActivity> activityScenario = ActivityScenario.launch(ProfileActivity.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
        DatabaseReference ref  = db.getReference().child("Users").child(user.getUid()).child("Username");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.getValue().toString();
                onView(withId(R.id.profileUsername)).check(matches(withText(userName)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ;
            }

        });
    }

    @Test
    public void test_firstName(){

        ActivityScenario<ProfileActivity> activityScenario = ActivityScenario.launch(ProfileActivity.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
        DatabaseReference ref  = db.getReference().child("Users").child(user.getUid()).child("First name");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firstName = dataSnapshot.getValue().toString();
                onView(withId(R.id.profileName)).check(matches(withText(firstName)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ;
            }

        });
    }

    @Test
    public void test_lastName(){

        ActivityScenario<ProfileActivity> activityScenario = ActivityScenario.launch(ProfileActivity.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
        DatabaseReference ref  = db.getReference().child("Users").child(user.getUid()).child("Last name");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lastName = dataSnapshot.getValue().toString();
                onView(withId(R.id.profileName)).check(matches(withText(lastName)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ;
            }

        });
    }

    @Test
    public void test_Description(){

        ActivityScenario<ProfileActivity> activityScenario = ActivityScenario.launch(ProfileActivity.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
        DatabaseReference ref  = db.getReference().child("Users").child(user.getUid()).child("Description");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String description = dataSnapshot.getValue().toString();
                onView(withId(R.id.profileDescription)).check(matches(withText(description)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ;
            }

        });
    }





}
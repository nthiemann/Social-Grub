package com.example.socialgrub;

import android.content.Intent;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import java.util.regex.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class EditProfileActivityTest {
/*
    @Rule
    public IntentsTestRule<EditProfileActivity> intentsTestRule = new IntentsTestRule<>(EditProfileActivity.class);


    @Test
    public void test_validateGalleryIntent() {

        assertThat(intentTestRule).hasAction(Intent.ACTION_VIEW);




    }
    */

    @Test
    public void test_visibilityEditProfile() {
        ActivityScenario<EditProfileActivity> activityScenario = ActivityScenario.launch(EditProfileActivity.class);

        onView(withId(R.id.editProfileID)).check(matches(isDisplayed()));


    }

    @Test
    public void test_changeButton() {

        ActivityScenario<EditProfileActivity> activityScenario = ActivityScenario.launch(EditProfileActivity.class);
        onView(withId(R.id.change_button)).perform(click());



        /*
            Still trying to figure out how to test this
        */
    }



    @Test
    public void test_username_change(){
        ActivityScenario<EditProfileActivity> activityScenario = ActivityScenario.launch(EditProfileActivity.class);

        onView(withId(R.id.username_editText)).perform(typeText("allecp0215"));
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.username_editText)).check(matches(withText("allecp215")));

    }


}
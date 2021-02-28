package com.example.socialgrub;

import androidx.annotation.ContentView;
import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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



}
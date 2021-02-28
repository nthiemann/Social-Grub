package com.example.socialgrub;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class SettingsActivityTest {

    @Test
    public void test_visibilitySettings() {

        ActivityScenario<SettingsActivity> activityScenario = ActivityScenario.launch(SettingsActivity.class);
        onView(withId(R.id.settingsPageID)).check(matches(isDisplayed()));


    }

    @Test
    public void test_functionality() {
        ActivityScenario<SettingsActivity> activityScenario = ActivityScenario.launch(SettingsActivity.class);

        onView(withId(R.id.resetPasswordButton)).perform(click());
        onView(withId(R.id.forgotPasswordID)).check(matches(isDisplayed()));

        pressBack();

        onView(withId(R.id.settingsPageID)).check(matches(isDisplayed()));

        onView(withId(R.id.logOutofAccountButton)).perform(click());
        onView(withId(R.id.mainID)).check(matches(isDisplayed()));



    }

}
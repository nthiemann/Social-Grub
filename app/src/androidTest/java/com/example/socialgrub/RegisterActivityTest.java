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

public class RegisterActivityTest {

    @Test
    public void test_visbilityRegisterPage() {

        ActivityScenario<RegisterActivity> activityScenario = ActivityScenario.launch(RegisterActivity.class);
        onView(withId(R.id.registerID)).check(matches(isDisplayed()));

    }

    //will test the actual back button on app screen
    @Test
    public void test_navigationRegisterPage1() {

        ActivityScenario<RegisterActivity> activityScenario = ActivityScenario.launch(RegisterActivity.class);
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.mainID)).check(matches(isDisplayed()));


    }

    //will test the back navigation built into our mobile phone
    @Test
    public void test_navigationRegisterPage2() {

        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.registerID)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.mainID)).check(matches(isDisplayed()));


    }


}
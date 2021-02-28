package com.example.socialgrub;

import android.widget.Toast;

import androidx.appcompat.widget.WithHint;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;

import org.junit.Test;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
        public void test_visibilityMainActivity() {

        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.mainID)).check(matches(isDisplayed()));


    }

    @Test
    public void test_visibilitySignInRegisterButton() {

        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.sign_in)).check(matches(isDisplayed()));
        onView(withId(R.id.register_button)).check(matches(isDisplayed()));

    }

    @Test
    public void test_navigationToRegister() {

        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.registerID)).check(matches(isDisplayed()));
    }


    @Test
    public void test_allPossibleErrorsWhenLoggingIn() {

        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.sign_in)).perform(click());
        onView(withId(R.id.mainID)).check(matches(isDisplayed()));
        onView(withId(R.id.enter_email)).check(matches(hasErrorText("Email is required!")));


        //email format is correct but not a credential stored in data base
        String invalidEmail = "invalid@gmail.com";
        onView(withId(R.id.enter_email)).perform(click());
        onView(withId(R.id.enter_email)).perform(typeText(invalidEmail));
        pressBack();

        onView(withId(R.id.mainID)).check(matches(isDisplayed()));
        onView(withId(R.id.sign_in)).perform(click());
        onView(withId(R.id.enter_password)).check(matches(hasErrorText( "Password is required")));


        //start a new activity to test Minimum password length is six characters long error
        ActivityScenario<MainActivity> activityScenario2 = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.enter_email)).perform(click());
        onView(withId(R.id.enter_email)).perform(typeText(invalidEmail));
        pressBack();

        String lessThanSixCharacters = "Hello";
        onView(withId(R.id.mainID)).check(matches(isDisplayed()));

        onView(withId(R.id.enter_password)).perform(typeText(lessThanSixCharacters));
        pressBack();
        onView(withId(R.id.sign_in)).perform(click());
        onView(withId(R.id.enter_password)).check(matches(hasErrorText
                ( "Minimum password length is six characters")));



    }

    @Test
    public void test_toastForInvalidCredentials() {

        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        String expectedStringEmail = "ricardo.mangandi@gmail.com"; //will test this specific login

        onView(withId(R.id.enter_email)).perform(click());

        //enter email
        onView(withId(R.id.enter_email)).perform(typeText(expectedStringEmail));
        pressBack();

        //will test this specific login, login is incorrect
        String expectedStringPassword = "Daniel1020";
        onView(withId(R.id.mainID)).check(matches(isDisplayed()));
        onView(withId(R.id.enter_password)).perform(click());

        //enter password
        onView(withId(R.id.enter_password)).perform(typeText(expectedStringPassword));
        pressBack();

        onView(withId(R.id.sign_in)).perform(click());


        onView(withText("Failed to login, Please check your credentials")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

    }


    @Test
    public void test_forgotPasswordButton() {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.forgotPassword)).perform(click());

        onView(withId(R.id.forgotPasswordID)).check(matches(isDisplayed()));


    }

}
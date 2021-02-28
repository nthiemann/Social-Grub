package com.example.socialgrub;

import android.view.KeyEvent;

import androidx.annotation.ContentView;
import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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

    @Test
    public void test_allPossibleErrorsOnRegister() {
        ActivityScenario<RegisterActivity> activityScenario = ActivityScenario.launch(RegisterActivity.class);
        onView(withId(R.id.registerID)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmRegister)).perform(click());
        onView(withId(R.id.registerEnterName)).perform(click()).check(matches(hasErrorText("First name required")));


        String validFirstName = "John";


        onView(withId(R.id.registerEnterName)).perform(typeText(validFirstName));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());
        onView(withId(R.id.editLastname)).perform(click()).check(matches(hasErrorText("Last name required")));



        String validLastName = "Smith";

        onView(withId(R.id.editLastname)).perform(typeText(validLastName));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());
        onView(withId(R.id.registerEnterUsername)).perform(click()).check(matches(hasErrorText("Username required")));



        String validUsername = "JohnSmith";

        onView(withId(R.id.registerEnterUsername)).perform(typeText(validUsername));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());
        onView(withId(R.id.enterEmail)).perform(click()).check(matches(hasErrorText("Email required")));


        String invalidEmailFormat = "johnsmith.com";

        onView(withId(R.id.enterEmail)).perform(typeText(invalidEmailFormat));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());
        onView(withId(R.id.enterEmail)).perform(click()).check(matches(hasErrorText("Valid email required")));



        onView(withId(R.id.enterEmail)).perform(replaceText(invalidEmailFormat)).perform(clearText());

        String validEmailFormat = "Deansmith@gmail.com";

        onView(withId(R.id.enterEmail)).perform(typeText(validEmailFormat));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());
        onView(withId(R.id.registerEnterPassword)).perform(click()).check(matches(hasErrorText("Password required")));




        String invalidPasswordLessThanSixChars = "Hello";

        onView(withId(R.id.registerEnterPassword)).perform(typeText(invalidPasswordLessThanSixChars));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());
        onView(withId(R.id.registerEnterPassword)).perform(click()).check(matches
                (hasErrorText("Password must be at least 6 characters long")));


        onView(withId(R.id.registerEnterPassword)).perform(replaceText(invalidEmailFormat)).perform(clearText());

        String validPassword = "ValidPassword";

        onView(withId(R.id.registerEnterPassword)).perform(typeText(validPassword));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());
        onView(withId(R.id.registerConfirmPassword)).perform(click()).check(matches(hasErrorText("Passwords do not match")));



        onView(withId(R.id.registerConfirmPassword)).perform(typeText(validPassword));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());
    }


    @Test
    public void test_toastFailedToRegister() {


        ActivityScenario<RegisterActivity> activityScenario = ActivityScenario.launch(RegisterActivity.class);
        onView(withId(R.id.registerID)).check(matches(isDisplayed()));


        String validFirstName = "John";


        onView(withId(R.id.registerEnterName)).perform(typeText(validFirstName));
        pressBack();

        String validLastName = "Smith";

        onView(withId(R.id.editLastname)).perform(typeText(validLastName));
        pressBack();


        String validUsername = "JohnSmith";

        onView(withId(R.id.registerEnterUsername)).perform(typeText(validUsername));
        pressBack();

        //user already exists
        String validEmailFormat = "ricardo.mangandi@gmail.com";

        onView(withId(R.id.enterEmail)).perform(typeText(validEmailFormat));
        pressBack();

        String validPassword = "ValidPassword";

        onView(withId(R.id.registerEnterPassword)).perform(typeText(validPassword));
        pressBack();


        onView(withId(R.id.registerConfirmPassword)).perform(typeText(validPassword));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());


        onView(withText("Failed to register user")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }


    //how to use:
    //If you are going to use this test be sure that the user email entered is not already in our database
    //If the entered email is already in our database test will fail

/*
    @Test //current test will fail and pass since it cannot authenticate user to database
    public void test_signUserIfAllValid() {

        ActivityScenario<RegisterActivity> activityScenario = ActivityScenario.launch(RegisterActivity.class);
        onView(withId(R.id.registerID)).check(matches(isDisplayed()));

        //modify as needed
        String validFirstName = "John";


        onView(withId(R.id.registerEnterName)).perform(typeText(validFirstName));
        pressBack();

        //modify as needed
        String validLastName = "Smith";

        onView(withId(R.id.editLastname)).perform(typeText(validLastName));
        pressBack();

        //modify as needed
        String validUsername = "JohnSmith";

        onView(withId(R.id.registerEnterUsername)).perform(typeText(validUsername));
        pressBack();

        //modify as needed
        String validEmailFormat = "john@gmail.com";

        onView(withId(R.id.enterEmail)).perform(typeText(validEmailFormat));
        pressBack();

        //modify as needed
        String validPassword = "ValidPassword";

        onView(withId(R.id.registerEnterPassword)).perform(typeText(validPassword));
        pressBack();


        onView(withId(R.id.registerConfirmPassword)).perform(typeText(validPassword));
        pressBack();
        onView(withId(R.id.confirmRegister)).perform(click());





    }
    */

}
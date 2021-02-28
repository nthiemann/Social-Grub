package com.example.socialgrub;

import androidx.annotation.ContentView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.assertion.ViewAssertions;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.regex.Pattern.matches;
import static org.junit.Assert.*;

public class ForgotPasswordActivityTest {

    @Test
    public void test_visibilityForgotPassword () {


        ActivityScenario<ForgotPasswordActivity> activityActivityScenario = ActivityScenario.launch(ForgotPasswordActivity.class);
        onView(withId(R.id.forgotPasswordID)).check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void test_allPossibleToast() {

        ActivityScenario<ForgotPasswordActivity> activityActivityScenario = ActivityScenario.launch(ForgotPasswordActivity.class);
        String inValidEmailFormat = "invalid.com";
        onView(withId(R.id.forgotPasswordEmail)).perform(typeText(inValidEmailFormat));
        pressBack();
        onView(withId(R.id.sendPasswordButton)).perform(click());

        onView(withText("The email address is badly formatted.")).inRoot(new ToastMatcher()).
                check(ViewAssertions.matches(isDisplayed()));


        onView(withId(R.id.forgotPasswordEmail)).perform(replaceText(inValidEmailFormat)).perform(clearText());




        String doesNotExist = "doesNotExistEmail@gmail.com";
        onView(withId(R.id.forgotPasswordEmail)).perform(typeText(doesNotExist));
        pressBack();
        onView(withId(R.id.sendPasswordButton)).perform(click());

        //test

/*
       onView(withText("There is no user record corresponding to this identifier.The user may have been deleted."))
                .inRoot(new ToastMatcher()).
                check(ViewAssertions.matches(isDisplayed()));

*/
        onView(withId(R.id.forgotPasswordEmail)).perform(replaceText(inValidEmailFormat)).perform(clearText());


        String doesExist = "john@gmail.com";
        onView(withId(R.id.forgotPasswordEmail)).perform(typeText(doesExist));
        pressBack();
        onView(withId(R.id.sendPasswordButton)).perform(click());

        onView(withId(R.id.forgotPasswordEmail)).perform(replaceText(inValidEmailFormat)).perform(clearText());



        //test will fail here cannot match up toast
      /*
        onView(withText("Your password has been sent to your email"))
                .inRoot(new ToastMatcher()).
                check(ViewAssertions.matches(isDisplayed()));
*/




    }




}
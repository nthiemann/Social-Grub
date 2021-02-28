package com.example.socialgrub;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class CreatePostTest {

    @Test
    public void test_visibilityCreatePost() {
        ActivityScenario<CreatePost> activityScenario = ActivityScenario.launch(CreatePost.class);
        onView(withId(R.id.createRecipePageID)).check(matches(isDisplayed()));


    }

    @Test
    public void test_cancelButton() {
        ActivityScenario<CreatePost> activityScenario = ActivityScenario.launch(CreatePost.class);
        onView(withId(R.id.postCancelButton)).perform(click());
        onView(withId(R.id.explorePageID)).check(matches(isDisplayed()));

    }


    @Test
    public void test_allPossibleErrorsOnCreatePost() {
        ActivityScenario<CreatePost> activityScenario = ActivityScenario.launch(CreatePost.class);

        onView(withId(R.id.postRecipeButton)).perform(click());
        onView(withId(R.id.recipeTitleInput)).perform(click()).
                check(matches(hasErrorText("Recipe title required")));

        String validTitleName = "Recipe Title";

        onView(withId(R.id.recipeTitleInput)).perform(typeText(validTitleName));
        pressBack();
        onView(withId(R.id.postRecipeButton)).perform(click());

        onView(withId(R.id.descriptionBox)).perform(click()).
                check(matches(hasErrorText("Please provide a brief description")));




    }

    //to be continued


}
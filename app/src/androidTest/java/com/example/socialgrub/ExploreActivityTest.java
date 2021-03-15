package com.example.socialgrub;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.util.regex.Pattern.matches;
import static org.junit.Assert.*;

public class ExploreActivityTest {

    @Test
    public void test_visbilityExplorePage() {
        ActivityScenario<ExploreActivity> activityScenario = ActivityScenario.launch(ExploreActivity.class);
        onView(withId(R.id.explorePageID)).check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void test_buttonFunctionalities() {
        ActivityScenario<ExploreActivity> activityScenario = ActivityScenario.launch(ExploreActivity.class);
        onView(withId(R.id.settings_button)).perform(click());
        onView(withId(R.id.settingsPageID)).check(ViewAssertions.matches(isDisplayed()));

        pressBack();
        onView(withId(R.id.explorePageID)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.createRecipePage)).perform(click());
        onView(withId(R.id.createRecipePageID)).check(ViewAssertions.matches(isDisplayed()));

        pressBack();
        onView(withId(R.id.explorePageID)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.profileID)).check(ViewAssertions.matches(isDisplayed()));

        pressBack();
        onView(withId(R.id.explorePageID)).check(ViewAssertions.matches(isDisplayed()));



    }

    @Test
    public void test_scrollFunction() {

        ActivityScenario<ExploreActivity> activityScenario = ActivityScenario.launch(ExploreActivity.class);
        Espresso.onView(ViewMatchers.withId(R.id.listOfPostsRecycler)).perform(ViewActions.swipeDown());


    }


}
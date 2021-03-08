package com.example.socialgrub;

import androidx.test.core.app.ActivityScenario;

import junit.framework.TestCase;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AddDirectionsTest extends TestCase {


    @Test
    public void test_visibilityDirections() {

        ActivityScenario<AddDirections> activityScenario = ActivityScenario.launch(AddDirections.class);
        onView(withId(R.id.directionsID)).check(matches(isDisplayed()));

    }

    @Test
    public void test_functionality() {
        ActivityScenario<AddDirections> activityScenario = ActivityScenario.launch(AddDirections.class);

        onView(withId(R.id.editTextDirection1)).perform(click());
        onView(withId(R.id.editTextDirection1)).perform(typeText("Boil Water"));
        pressBack();

        onView(withId(R.id.editTextDirection2)).perform(click());
        onView(withId(R.id.editTextDirection2)).perform(typeText("Put noodles in water"));
        pressBack();


        onView(withId(R.id.addBothDirectionsButton)).perform(click());

        onView(withId(R.id.toAddTagsButton)).perform(click());
        onView(withId(R.id.addTagsID)).check(matches(isDisplayed()));

    }
}
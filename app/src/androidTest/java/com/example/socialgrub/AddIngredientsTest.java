package com.example.socialgrub;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.typeText;
import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class AddIngredientsTest {

    @Test
    public void test_visibilityIngredients() {

        ActivityScenario<AddIngredients> activityScenario = ActivityScenario.launch(AddIngredients.class);
        onView(withId(R.id.ingredientsID)).check(matches(isDisplayed()));

    }

    @Test
    public void test_functionality() {
        ActivityScenario<AddIngredients> activityScenario = ActivityScenario.launch(AddIngredients.class);

        onView(withId(R.id.editNameOfIngredient1)).perform(click());
        onView(withId(R.id.editNameOfIngredient1)).perform(typeText("Test Ingredient 1"));
        pressBack();

        onView(withId(R.id.editTextValueOfIngredient1)).perform(typeText("10"));
        pressBack();

        onView(withId(R.id.editNameOfIngredient2)).perform(click());
        onView(withId(R.id.editNameOfIngredient2)).perform(typeText("Test Ingredient 2"));
        pressBack();

        onView(withId(R.id.editTextValueOfIngredient2)).perform(typeText("10"));
        pressBack();

        onView(withId(R.id.addBothIngredientsButton)).perform(click());

        onView(withId(R.id.toDirectionPageButtons)).perform(click());


        onView(withId(R.id.directionsID)).check(matches(isDisplayed()));

    }
}

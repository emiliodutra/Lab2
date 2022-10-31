package com.example.lab2;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordEdit));

        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView( withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }

    /**
     * this method tests if the program will detect the password is missing an uppercase letter
     */
    @Test
    public void testFindMissingUpperCase() {
        //finds the textView
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordEdit));
        //type in the password
        appCompatEditText.perform(replaceText("password1234#$*"));

        ViewInteraction materialButton = onView( withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }
    /**
     * this method tests if the program will detect the password is missing an lowercase letter
     */
    @Test
    public void testFindMissingLowerCase() {
        //finds the textView
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordEdit));
        //type in the password
        appCompatEditText.perform(replaceText("PASS1234#$*"));

        ViewInteraction materialButton = onView( withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }
    /**
     * this method tests if the program will detect the password is missing a number
     */
    @Test
    public void testFindMissingNumber() {
        //finds the textView
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordEdit));
        //type in the password
        appCompatEditText.perform(replaceText("pASS#$*"));

        ViewInteraction materialButton = onView( withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }
    /**
     * this method tests if the program will detect the password is missing a special letter
     */
    @Test
    public void testFindMissingSpecial() {
        //finds the textView
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordEdit));
        //type in the password
        appCompatEditText.perform(replaceText("pASS123"));

        ViewInteraction materialButton = onView( withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));

    }
    /**
     * this method tests if the program will accept a password with the requirements
     */
    @Test
    public void testHasRequirements() {
        //finds the textView
        ViewInteraction appCompatEditText = onView(withId(R.id.passwordEdit));
        //type in the password
        appCompatEditText.perform(replaceText("pASS123#$"));

        ViewInteraction materialButton = onView( withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your Password meets the requirements")));

    }
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

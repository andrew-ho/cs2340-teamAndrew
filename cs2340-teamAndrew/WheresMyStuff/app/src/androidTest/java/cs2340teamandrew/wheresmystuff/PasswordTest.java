package cs2340teamandrew.wheresmystuff;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import controller.LoginScreenActivity;
import cs2340teamandrew.wheresmystuff.R;

import static android.R.string.cancel;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.core.deps.guava.base.Predicates.not;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Tests checking valid password for registration
 * @author Yvonne Yeh
 * @version 1.0
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class PasswordTest {

    @Rule
    public ActivityTestRule<LoginScreenActivity> mActivityTestRule = new ActivityTestRule<>(LoginScreenActivity.class);

    @Test
    public void passwordTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        // Test1: password too short
        // press register button
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction register = onView(
                allOf(withId(R.id.registration), withText("Register")));
        register.perform(scrollTo(), click());

        // input email and password
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction email = onView(
                withId(R.id.email));
        email.perform(scrollTo(), replaceText("hi@hi.com"), closeSoftKeyboard());

        ViewInteraction password = onView(
                withId(R.id.password));
        password.perform(scrollTo(), replaceText("hi"), closeSoftKeyboard());

        // press register button again
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        register.perform(scrollTo(), click());

        // check error message
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction errorMessage = onView(
                allOf(withId(R.id.password), isDisplayed()));

        errorMessage.check(matches(hasErrorText("This password is too short")));

        // close
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pressBack();

        // test2: no password
        // press register button
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        register.perform(scrollTo(), click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        email.perform(scrollTo(), replaceText("idk@idk.com"), closeSoftKeyboard());
        password.perform(scrollTo(), replaceText(""), closeSoftKeyboard());
        register.perform(scrollTo(), click());

        errorMessage.check(matches(hasErrorText("This password is too short")));

        // Test 3: valid password
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pressBack();

        register.perform(scrollTo(), click());
        email.perform(scrollTo(), replaceText("good6@good.com"), closeSoftKeyboard());
        password.perform(scrollTo(), replaceText("password"), closeSoftKeyboard());

        register.perform(scrollTo(), click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction viewInteraction = onView(withText("Registered successfully")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
}
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;


/**
 * Checks if account is created
 * Created by Andrew Le on 7/18/2017.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterScreenActivityTest {

    @Rule
    public ActivityTestRule<LoginScreenActivity> mActivityTestRule = new ActivityTestRule<>(LoginScreenActivity.class);

    @Test
    public void registerScreenActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        final int SLEEPTIME = 2000;
        try {
            Thread.sleep(SLEEPTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                withId(R.id.password));
        appCompatEditText.perform(scrollTo(), replaceText("yo"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.password), withText("Invalid pass"), isDisplayed()));

        textView.check(matches(withText("Invalid pass")));

        try {
            Thread.sleep(SLEEPTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.password));
        appCompatEditText3.perform(scrollTo(), replaceText("password"), closeSoftKeyboard());

        assertEquals(cancel, false);
    }
}

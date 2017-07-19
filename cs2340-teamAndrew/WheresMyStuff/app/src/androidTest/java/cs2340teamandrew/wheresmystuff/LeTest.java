package cs2340teamandrew.wheresmystuff;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

import controller.LoginScreenActivity;

/**
 * Created by Andrew on 7/18/2017.
 */

public class LeTest {
    @Rule
    public ActivityTestRule<LoginScreenActivity> mActivityTestRule = new ActivityTestRule<>(LoginScreenActivity.class);
}

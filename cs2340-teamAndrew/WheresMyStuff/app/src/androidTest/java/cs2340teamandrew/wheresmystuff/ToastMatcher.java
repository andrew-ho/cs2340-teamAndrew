package cs2340teamandrew.wheresmystuff;

import android.os.IBinder;
import android.support.test.espresso.Root;
import android.support.test.espresso.core.deps.dagger.Provides;
import android.view.WindowManager;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Custom Matcher to test for Toast
 * Created by Alex on 7/18/2017.
 */

public class ToastMatcher extends TypeSafeMatcher<Root>{

    @Override    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                //means this window isn't contained by any other windows.
            }
        }
        return false;
    }
}

package com.github.batulovandrey.yandextranslator;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Checkable;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.isA;

/**
 * UI test of {@link MainActivity}
 *
 * @author Andrey Batulov on 13/11/2017
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String INPUT_STRING = "hello world";
    private static final String EXPECTED_STRING = "привет мир";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testTranslation() throws InterruptedException {
        onView(withId(R.id.switch_compat)).perform(setChecked(true));
        onView(withId(R.id.input_edit_text)).perform(typeText(INPUT_STRING), closeSoftKeyboard());
        onView(withId(R.id.translate_button)).perform(click());
        onView(withId(R.id.translation_text_view)).check(matches(withText(EXPECTED_STRING)));
    }

    @Test
    public void testChangeLanguage() {
        onView(withId(R.id.switch_compat)).perform(click());
        onView(withId(R.id.translate_button)).check(matches(withText(R.string.translate_ru)));
    }

    public ViewAction setChecked(final boolean checked) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return new Matcher<View>() {
                    @Override
                    public boolean matches(Object item) {
                        return isA(Checkable.class).matches(item);
                    }

                    @Override
                    public void describeMismatch(Object item, Description mismatchDescription) {
                    }

                    @Override
                    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
                    }

                    @Override
                    public void describeTo(Description description) {
                    }
                };
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View view) {
                Checkable checkable = (Checkable) view;
                checkable.setChecked(checked);
            }
        };
    }
}
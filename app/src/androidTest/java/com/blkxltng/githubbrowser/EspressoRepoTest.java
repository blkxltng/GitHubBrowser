package com.blkxltng.githubbrowser;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoRepoTest {

    @Rule
    public ActivityTestRule<MainActivity> loginActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static final int THREAD_SLEEP_TIME_1000 = 1000;
    private static final int THREAD_SLEEP_TIME_5000 = 5000;
    private static final String sampleGitHubOrganization = "NYTimes";
    private static final String sampleGitHubOrganizationName = "The New York Times";


    @Before
    public void init() {
        loginActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    //Searches for an organization and confirms that it has loaded
    @Test
    public void searchOrganizationAndCheckDisplayed() {
        onView(withId(R.id.fragmentSearch_organizationEditText)).perform(typeText(sampleGitHubOrganization), closeSoftKeyboard()); // Type in the organization
        onView(withId(R.id.fragmentSearch_searchButton)).perform(click());
        sleepThread(THREAD_SLEEP_TIME_1000);
        onView(withId(R.id.fragmentRepoList_organizationNameTextView)).check(matches(withText(sampleGitHubOrganizationName)));
    }

    //Searches for an organization and clicks on a repo. Also checks to see that the webview loads.
    @Test
    public void searchOrganizationAndLoadWebView() {
        onView(withId(R.id.fragmentSearch_organizationEditText)).perform(typeText(sampleGitHubOrganization), closeSoftKeyboard()); // Type in the organization
        onView(withId(R.id.fragmentSearch_searchButton)).perform(click());
        sleepThread(THREAD_SLEEP_TIME_1000);
        onView(withId(R.id.fragmentRepoList_organizationNameTextView)).check(matches(withText(sampleGitHubOrganizationName)));
        onView(withId(R.id.fragmentRepoList_repoRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        sleepThread(THREAD_SLEEP_TIME_1000);
        onView(withId(R.id.fragmentWebView_webView)).check(matches(isDisplayed()));
    }

    private static void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}

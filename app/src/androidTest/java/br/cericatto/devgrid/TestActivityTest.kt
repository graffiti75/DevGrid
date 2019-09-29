package br.cericatto.devgrid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.cericatto.devgrid.rules.OkHttpIdlingResourceRule
import br.cericatto.devgrid.view.activity.TestActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * TestActivityTest.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 28, 2019
 */
@RunWith(AndroidJUnit4::class)
class TestActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<TestActivity> = ActivityTestRule(
        TestActivity::class.java)

    @get:Rule
    var okHttpIdlingResourceRule = OkHttpIdlingResourceRule()

    // TODO: https://github.com/square/retrofit/issues/2393

    @Test
    fun givenRetrofitApiCall_whenCallingGetRepos_thenCheckSuccessfullResponse() {
//        val idlingResource = OkHttp3IdlingResource.create(
//            "okhttp", OkHttpProvider.instance)
//        IdlingRegistry.getInstance().register(idlingResource)
        onView(withId(R.id.github_user_id))
            .check(matches(withText("42342883")))
//        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}
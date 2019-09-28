package br.cericatto.devgrid

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import br.cericatto.devgrid.presenter.api.OkHttpProvider
import br.cericatto.devgrid.view.TestActivity
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.Rule
import org.junit.Test

class TestActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<TestActivity> = ActivityTestRule(TestActivity::class.java)

    @Test
    fun givenRetrofitApiCall_whenCallingGetRepos_thenCheckSuccessfullResponse() {
        val idlingResource = OkHttp3IdlingResource.create(
            "okhttp", OkHttpProvider.okHttpInstance)
        IdlingRegistry.getInstance().register(idlingResource)
        onView(withId(R.id.text_view))
            .check(matches(withText("42342883")))
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}
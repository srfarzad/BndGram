package com.app.bndgram;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.app.bndgram.ui.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AndroidUiTest {


    @Rule
    public ActivityTestRule<LoginActivity> mActivity =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void click_googleMap(){

        onView(withId(R.id.btn_map))
                .perform(click());


    }




}

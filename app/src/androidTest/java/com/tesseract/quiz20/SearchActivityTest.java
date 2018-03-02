package com.tesseract.quiz20;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;


public class SearchActivityTest {
    @Rule
    public ActivityTestRule<SearchActivity> mActivityTestRule = new ActivityTestRule<SearchActivity>(SearchActivity.class);

    public SearchActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.country);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
    mActivity = null;
    }

}
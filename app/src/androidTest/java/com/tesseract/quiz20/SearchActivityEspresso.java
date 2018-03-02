package com.tesseract.quiz20;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class SearchActivityEspresso {
    @Rule
    public ActivityTestRule<SearchActivity> mActivityTestRule = new ActivityTestRule<SearchActivity>(SearchActivity.class);

    @Test
    public void testChangeIntent(){
        Intent intent = new Intent(mActivityTestRule.getActivity(), ImageActivity.class);
        mActivityTestRule.launchActivity(intent);
    }


}

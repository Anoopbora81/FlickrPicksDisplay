package com.test.flickr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewParent;

import com.test.flickr.R;
import com.test.flickr.adapters.MediumViewAdapter;
import com.test.flickr.constants.ConstantValues;
import com.test.flickr.interfaces.ICurrentAppData;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import javax.inject.Inject;

public class MediumViewActivity extends RoboActivity {
    @InjectView(R.id.pager)
    private ViewPager viewPager;
    @Inject
    ICurrentAppData currentAppData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_view);

        MediumViewAdapter mediumViewAdapter = new MediumViewAdapter(MediumViewActivity.this);
        viewPager.setAdapter(mediumViewAdapter);
        viewPager.setCurrentItem(currentAppData.getCurrentPosition());
    }
}
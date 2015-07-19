package com.test.flickr.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.test.flickr.activities.MediumViewActivity;
import com.test.flickr.interfaces.ICurrentAppData;

import roboguice.RoboGuice;

import javax.inject.Inject;

public class ImageGridViewAdapter extends BaseAdapter {

    @Inject
    ICurrentAppData currentAppData;
    private Activity activity;


    public ImageGridViewAdapter(Activity activity) {
        RoboGuice.getInjector(activity).injectMembers(this);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return currentAppData.getImageInfos().size();
    }

    @Override
    public Object getItem(int position) {
        return currentAppData.getImageInfos().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView result;
        if (convertView == null)
            result = new ImageView(activity);
        else
            result = (ImageView) convertView;


        result.setScaleType(ImageView.ScaleType.CENTER);
        result.setImageBitmap(currentAppData.getImageInfos().get(position).getThumbnailBitmap());
        result.setOnClickListener(new ImageGridViewCellOnClickListener(position));

        return result;
    }

    class ImageGridViewCellOnClickListener implements View.OnClickListener {
        private int position;

        public ImageGridViewCellOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            currentAppData.setCurrentPosition(position);
            Intent intent = new Intent(activity, MediumViewActivity.class);
            activity.startActivity(intent);
        }
    }
}

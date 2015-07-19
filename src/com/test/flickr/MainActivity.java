package com.test.flickr;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.GridView;

import com.test.flickr.R;
import com.hintdesk.core.utils.DeviceUtil;
import com.test.flickr.adapters.ImageGridViewAdapter;
import com.test.flickr.constants.ConstantValues;
import com.test.flickr.flickrutil.Flickr;
import com.test.flickr.flickrutil.Photo;
import com.test.flickr.flickrutil.Size;
import com.test.flickr.interfaces.ICurrentAppData;
import com.test.flickr.vo.ImageInfo;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import javax.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends RoboActivity {
    @InjectView(R.id.gridView)
    private GridView gridView;
    @Inject
    ICurrentAppData currentAppData;
    private ImageGridViewAdapter imageGridViewAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeComponents();
        new LoadImagesFromFlickrTask().execute();
    }

    private void initializeComponents() {
        float spacing = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                ConstantValues.GRIDVIEW_SPACING, getResources().getDisplayMetrics());
        gridView.setNumColumns(DeviceUtil.getDeviceDimensions(MainActivity.this).x / ConstantValues.GRIDVIEW_COLUMN_WIDTH);
        gridView.setPadding((int) spacing, (int) spacing, (int) spacing, (int) spacing);
        gridView.setVerticalSpacing((int) spacing);
        gridView.setHorizontalSpacing((int) spacing);
    }

    class LoadImagesFromFlickrTask extends AsyncTask<String, Integer, List<ImageInfo>> {
        private ProgressDialog progressDialog;
        private Integer totalCount, currentIndex;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading images from Flickr. Please wait...");
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setMessage(String.format("Loading images from Flickr %s/%s. Please wait...", values[0], values[1]));
        }

        @Override
        protected List<ImageInfo> doInBackground(String... params) {
            Flickr flickr = new Flickr(ConstantValues.FLICKR_API_KEY, ConstantValues.FLICKR_FORMAT);
            List<Photo> photos = flickr.getPhotoSets().getPhotos(ConstantValues.PHOTOSET_ID);
            List<ImageInfo> result = new ArrayList<ImageInfo>();
            totalCount = photos.size();
            currentIndex = 0;
            for (Photo photo : photos) {
                currentIndex++;
                List<Size> sizes = flickr.getPhotos().getSizes(photo.getId());
                String thumbnailUrl = sizes.get(0).getSource();
                String mediumUrl = sizes.get(4).getSource();
                InputStream inputStreamThumbnail = null, inputStreamMedium = null;
                try {
                    inputStreamThumbnail = new URL(thumbnailUrl).openStream();
                    inputStreamMedium = new URL(mediumUrl).openStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap bitmapThumbnail = BitmapFactory.decodeStream(inputStreamThumbnail);
                Bitmap bitmapMedium = BitmapFactory.decodeStream(inputStreamMedium);
                result.add(new ImageInfo(photo.getTitle(), bitmapThumbnail, bitmapMedium));
                publishProgress(currentIndex, totalCount);
//                if (currentIndex>2)
//                    break;
            }
            currentAppData.setImageInfos(result);
            return result;
        }

        @Override
        protected void onPostExecute(List<ImageInfo> s) {
            progressDialog.dismiss();
            imageGridViewAdapter = new ImageGridViewAdapter(MainActivity.this);
            gridView.setAdapter(imageGridViewAdapter);
            super.onPostExecute(s);
        }
    }
//     @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//    	// TODO Auto-generated method stub
//    	
//    	//this.moveTaskToBack(true);
//    	return false;
//    }
}

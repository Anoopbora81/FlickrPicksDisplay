package com.test.flickr.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.flickr.R;
import com.hintdesk.core.activities.AlertMessageBox;
import com.hintdesk.core.utils.IOUtil;
import com.test.flickr.interfaces.ICurrentAppData;

import roboguice.RoboGuice;

import javax.inject.Inject;

import java.io.*;


public class MediumViewAdapter extends PagerAdapter {
    @Inject
    ICurrentAppData currentAppData;
    private Activity activity;
    private ImageView imageView;
    private TextView textView;

    public MediumViewAdapter(Activity activity) {
        RoboGuice.getInjector(activity).injectMembers(this);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return currentAppData.getImageInfos().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (LinearLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_medium_view, container, false);

        imageView = (ImageView) viewLayout.findViewById(R.id.imageView);
        imageView.setImageBitmap(currentAppData.getImageInfos().get(position).getMediumBitmap());

        ImageButton buttonBack = (ImageButton) viewLayout.findViewById(R.id.imageButtonBack);
        buttonBack.setOnClickListener(ButtonBackOnClickListener);

//        ImageButton buttonDownload = (ImageButton) viewLayout.findViewById(R.id.imageButtonDownload);
//        buttonDownload.setOnClickListener(ButtonDownloadOnClickListener);

//        textView = (TextView) viewLayout.findViewById(R.id.textViewName);
//        textView.setText(currentAppData.getImageInfos().get(position).getName());


//        new LoadMediumImageTask().execute();
        ((ViewPager) container).addView(viewLayout);
        return viewLayout;
    }

//    private View.OnClickListener ButtonDownloadOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Bitmap bitmap = currentAppData.getImageInfos().get(currentAppData.getCurrentPosition()).getMediumBitmap();
//            String fileName = currentAppData.getImageInfos().get(currentAppData.getCurrentPosition()).getName() + ".png";
//            String hdImagesPath = Environment.getExternalStorageDirectory() + "/hdimages/";
//            File hdImagesPathDir = new File(hdImagesPath);
//            hdImagesPathDir.mkdir();
//            String filePath = IOUtil.pathCombine(hdImagesPath.toString(), fileName);
//            File filePathInfo = new File(filePath);
//            try {
//                filePathInfo.createNewFile();
//                FileOutputStream fileOutputStream = null;
//                fileOutputStream = new FileOutputStream(filePathInfo);
//                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
//                bufferedOutputStream.flush();
//                bufferedOutputStream.close();
//                AlertMessageBox.Show(activity, "Info", "Successfully saved", AlertMessageBox.AlertMessageBoxIcon.Info);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    };

    private View.OnClickListener ButtonBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            activity.finish();
        }
    };

//    private class LoadMediumImageTask extends AsyncTask<String, String, Bitmap> {
//        private ProgressDialog progressDialog;
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
////            imageView.setImageBitmap(bitmap);
//            imageView.setImageBitmap(currentAppData.getImageInfos().get(currentAppData.getCurrentPosition()).getThumbnailBitmap());
//            progressDialog.dismiss();
//            super.onPostExecute(bitmap);
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            Bitmap bitmap = currentAppData.getMediumImage(currentAppData.getCurrentPosition());
//            if (bitmap == null) {
//                InputStream inputStream = null;
//                try {
//                    inputStream = new URL(currentAppData.getImageInfos().get(currentAppData.getCurrentPosition()).getMediumUrl()).openStream();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                currentAppData.setMediumImage(currentAppData.getCurrentPosition(),bitmap);
//            }
//            return bitmap;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(activity);
//            progressDialog.setMessage("Loading medium images from Flickr. Please wait...");
//            progressDialog.show();
//        }
//    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}

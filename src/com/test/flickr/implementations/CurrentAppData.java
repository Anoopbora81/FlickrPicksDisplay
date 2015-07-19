package com.test.flickr.implementations;

import android.graphics.Bitmap;

import com.google.inject.Singleton;
import com.test.flickr.interfaces.ICurrentAppData;
import com.test.flickr.vo.ImageInfo;

import roboguice.inject.ContextSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class CurrentAppData implements ICurrentAppData {
    List<ImageInfo> imageInfos;
    int currentPosition;
    List<Bitmap> mediumBitmaps;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }


    public Bitmap getMediumImage(int position) {
        return mediumBitmaps.get(position);
    }


    public void setMediumImage(int position, Bitmap bitmap) {
        mediumBitmaps.set(position, bitmap);
    }

    @Override
    public List<ImageInfo> getImageInfos() {
        return imageInfos;
    }

    @Override
    public void setImageInfos(List<ImageInfo> imageInfos) {
        this.imageInfos = imageInfos;
        mediumBitmaps = new ArrayList<Bitmap>(Collections.nCopies(imageInfos.size(), (Bitmap) null));
    }
}

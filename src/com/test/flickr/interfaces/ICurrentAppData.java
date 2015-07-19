package com.test.flickr.interfaces;

import android.graphics.Bitmap;

import com.google.inject.Singleton;
import com.test.flickr.vo.ImageInfo;

import java.util.List;

public interface ICurrentAppData {
    List<ImageInfo> getImageInfos();

    void setImageInfos(List<ImageInfo> imageInfos);

    int getCurrentPosition();

    void setCurrentPosition(int currentPosition);
}

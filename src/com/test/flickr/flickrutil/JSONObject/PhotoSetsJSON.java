package com.test.flickr.flickrutil.JSONObject;

import com.test.flickr.flickrutil.FlickrBaseItem;
import com.test.flickr.flickrutil.PhotoSet;


public class PhotoSetsJSON extends FlickrBaseItemJSON {
    public PhotoSet getPhotoset() {
        return photoset;
    }

    public void setPhotoset(PhotoSet photoset) {
        this.photoset = photoset;
    }

    private PhotoSet photoset;


}

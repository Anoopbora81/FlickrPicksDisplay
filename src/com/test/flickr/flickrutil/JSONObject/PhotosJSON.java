package com.test.flickr.flickrutil.JSONObject;

import com.test.flickr.flickrutil.Sizes;

public class PhotosJSON extends FlickrBaseItemJSON {
    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    private Sizes sizes;
}

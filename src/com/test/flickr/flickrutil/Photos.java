package com.test.flickr.flickrutil;


import com.hintdesk.core.utils.JSONHttpClient;
import com.test.flickr.flickrutil.JSONObject.PhotoSetsJSON;
import com.test.flickr.flickrutil.JSONObject.PhotosJSON;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Photos extends FlickrBaseItem {
    public Photos(String api_key, String format) {
        super(api_key, format);
    }

    public List<Size> getSizes(String photo_id) {

        JSONHttpClient jsonHttpClient = new JSONHttpClient();
        String url = String.format(FlickrUrls.flickr_photos_getSizes, format, api_key, photo_id);
        PhotosJSON photosJson = jsonHttpClient.Get(url, new ArrayList<NameValuePair>(), PhotosJSON.class);
        return photosJson.getSizes().getSize();

    }
}

package com.test.flickr.flickrutil.JSONObject;

import java.io.Serializable;


public class FlickrBaseItemJSON implements Serializable {
    private String stat;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}

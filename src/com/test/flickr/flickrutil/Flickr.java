package com.test.flickr.flickrutil;


public class Flickr extends FlickrBaseItem {


    public Flickr(String api_key, String format) {
        super(api_key, format);
        photoSets = new PhotoSets(api_key, format);
        photos = new Photos(api_key, format);
    }


    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    private Photos photos;

    private PhotoSets photoSets;

    public PhotoSets getPhotoSets() {
        return photoSets;
    }

    public void setPhotoSets(PhotoSets photoSets) {
        this.photoSets = photoSets;
    }
}

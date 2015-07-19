package com.test.flickr.infrastructure;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.test.flickr.implementations.CurrentAppData;
import com.test.flickr.interfaces.ICurrentAppData;


public class IOCModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(ICurrentAppData.class).to(CurrentAppData.class);
    }
}

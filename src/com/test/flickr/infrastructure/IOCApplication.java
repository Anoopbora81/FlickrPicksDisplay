package com.test.flickr.infrastructure;

import android.app.Application;
import roboguice.RoboGuice;

public class IOCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, RoboGuice.newDefaultRoboModule(this), new IOCModule());

    }
}
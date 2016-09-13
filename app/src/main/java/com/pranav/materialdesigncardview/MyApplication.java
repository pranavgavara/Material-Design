package com.pranav.materialdesigncardview;

import android.app.Application;
import android.content.Context;

/**
 * Created by Pranav on 8/28/2016.
 */
public class MyApplication extends Application {
    public static MyApplication sInstance;
    private static YouTubeDatabase youTubeDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }
    public static MyApplication getInstance(){
        return sInstance;
    }
    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
    public synchronized static YouTubeDatabase getYouTubeDatabase(){
        if(youTubeDatabase==null){
            youTubeDatabase=new YouTubeDatabase(getAppContext());
        }
        return youTubeDatabase;
    }
}

package xyz.app;

import android.app.Application;

import xyz.db.AppDatabase;

/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppDatabase.destroyInstance();
    }


}

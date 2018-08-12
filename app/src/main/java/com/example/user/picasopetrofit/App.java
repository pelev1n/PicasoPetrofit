package com.example.user.picasopetrofit;

import android.app.Application;
import android.content.Context;

import com.example.user.picasopetrofit.bd.DaoMaster;
import com.example.user.picasopetrofit.bd.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private static DaoSession mDaoSession;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"movie.db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public static Context getmContext() {
        return mContext;
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}

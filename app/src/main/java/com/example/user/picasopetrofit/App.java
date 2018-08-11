package com.example.user.picasopetrofit;

import android.app.Application;

import com.example.user.picasopetrofit.bd.DaoMaster;
import com.example.user.picasopetrofit.bd.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"movie.db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}

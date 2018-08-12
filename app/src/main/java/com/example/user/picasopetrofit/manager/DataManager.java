package com.example.user.picasopetrofit.manager;

import android.content.Context;

import com.example.user.picasopetrofit.App;
import com.example.user.picasopetrofit.RetrofitApi;
import com.example.user.picasopetrofit.bd.DaoSession;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;

public class DataManager {

    private static DataManager instants = null;
    private Context mContext;
    private DaoSession daoSession;
    private Picasso mPicaso;
    private Retrofit retrofitApi;

    DataManager () {
        this.mContext = App.getmContext();
        this.retrofitApi = RetrofitApi.getClient();
        this.daoSession = App.getDaoSession();
        this.mPicaso = new PicassoCache(mContext).getPicassoInstance();
    }

    public static DataManager getInstants() {
        if(instants == null) {
            instants = new DataManager();
        }
        return instants;
    }

    public Context getmContext() {
        return mContext;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public Picasso getmPicaso() {
        return mPicaso;
    }

    public Retrofit getRetrofitApi() {
        return retrofitApi;
    }
}

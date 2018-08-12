package com.example.user.picasopetrofit.bd;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Movie implements Parcelable {

    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String description;
    @SerializedName("backdrop_path")
    private String backdrop;

    public Movie() {}

    protected Movie(Parcel in) {
        title = in.readString();
        poster = in.readString();
        description = in.readString();
        backdrop = in.readString();
    }

    @Generated(hash = 1238002848)
    public Movie(String title, String poster, String description, String backdrop) {
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.backdrop = backdrop;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return /*"http://image.tmdb.org/t/p/w500" +*/ poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackdrop() {
        return /*"http://image.tmdb.org/t/p/w500"  + */ backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(description);
        parcel.writeString(backdrop);
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }
}
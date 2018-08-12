package com.example.user.picasopetrofit;

import com.example.user.picasopetrofit.bd.Movie;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Entity
    public static class ResponseModel {

            @SerializedName("page")
            private long page;

            @SerializedName("total_results")
            private long totalResults;

            @SerializedName("total_pages")
            private long totalPages;

            @SerializedName("results")
            private List<Movie> results;

            public long getPage() { return page; }
            public void setPage(long value) { this.page = value; }

            public long getTotalResults() { return totalResults; }
            public void setTotalResults(long value) { this.totalResults = value; }

            public long getTotalPages() { return totalPages; }
            public void setTotalPages(long value) { this.totalPages = value; }

            public List<Movie> getResults() { return results; }
            public void setResults(List<Movie> value) { this.results = value; }

    }
}

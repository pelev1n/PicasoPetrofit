package com.example.user.picasopetrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.picasopetrofit.bd.DaoSession;
import com.example.user.picasopetrofit.bd.Movie;
import com.example.user.picasopetrofit.bd.MovieDao;
import com.example.user.picasopetrofit.manager.DataManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    RetrofitApi retrofitApi;
    DaoSession mDaoSession;
    MovieDao movieDao;
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        dataManager = DataManager.getInstants();
        movieDao = dataManager.getDaoSession().getMovieDao();

/*        movieDao.deleteAll();*/
        getMovie();

    }

    public void setAdapter() {
        List<Movie> movies = movieDao.loadAll();

        movieAdapter = new MovieAdapter();
        movieAdapter.setMovieList(movies);
        recyclerView.setAdapter(movieAdapter);
    }

  private    void  getMovie(){

    MoviesApiService moviesApiService = dataManager.getRetrofitApi().create(MoviesApiService.class);

      Call<ResponseModel> call = moviesApiService.getPopularMovies("eb37bafc5fe27ad9ab86a74e72812c06");

      call.enqueue(new Callback<ResponseModel>() {
          @Override
          public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
              movieDao.insertOrReplaceInTx(response.body().getResults());
              setAdapter();
          }

          @Override
          public void onFailure(Call<ResponseModel> call, Throwable t) {

          }
      });

    }}

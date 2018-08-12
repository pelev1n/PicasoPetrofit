package com.example.user.picasopetrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.picasopetrofit.bd.DaoSession;
import com.example.user.picasopetrofit.bd.Movie;
import com.example.user.picasopetrofit.bd.MovieDao;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);
        mDaoSession = ((App) getApplication()).getDaoSession();
        movieDao = mDaoSession.getMovieDao();

        movieDao.deleteAll();


        getMovie();

    }

  private    void  getMovie(){
//    RestAdapter restAdapter = new RestAdapter.Builder()
//            .setEndpoint("http://api.themoviedb.org/3")
//            .setRequestInterceptor(new RequestInterceptor() {
//                @Override
//                public void intercept(RequestFacade request) {
//                    request.addEncodedQueryParam("api_key", "eb37bafc5fe27ad9ab86a74e72812c06");
//                }
//            })
//            .setLogLevel(RestAdapter.LogLevel.FULL)
//            .build();

    MoviesApiService moviesApiService = retrofitApi.getClient().create(MoviesApiService.class);

    //интерфейс реализован с помощию класса Refrofit  с базовым URL b конвертором
//      MoviesApiService service = restAdapter.create(MoviesApiService.class);

      /*Call<List<Movie>> call = moviesApiService.getPopularMovies("eb37bafc5fe27ad9ab86a74e72812c06");

      call.enqueue(new Callback<List<Movie>>() {
          @Override
          public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
              System.out.println("HELLO");
              movieAdapter.setMovieList(response.body());
          }

          @Override
          public void onFailure(Call<List<Movie>> call, Throwable t) {

          }
      });*/
      Call<ResponseModel> call = moviesApiService.getPopularMovies("eb37bafc5fe27ad9ab86a74e72812c06");

      call.enqueue(new Callback<ResponseModel>() {
          @Override
          public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

              movieDao.insertOrReplaceInTx(response.body().getResults());
              List<Movie> moviesDao = movieDao.loadAll();

              /*List<Movie> moviesApi = response.body().getResults();*/
              movieAdapter.setMovieList(moviesDao);
          }

          @Override
          public void onFailure(Call<ResponseModel> call, Throwable t) {

          }
      });

    }}

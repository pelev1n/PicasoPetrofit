package com.example.user.picasopetrofit;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.picasopetrofit.bd.Movie;
import com.example.user.picasopetrofit.manager.DataManager;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

        public static final String EXTRA_MOVIE = "movie";

        private Movie mMovie;
        ImageView backdrop;
        ImageView poster;
        TextView title;
        TextView description;
        DataManager dataManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            dataManager = DataManager.getInstants();
            setContentView(R.layout.activity_movie_detail);
            if (getIntent().hasExtra(EXTRA_MOVIE)) {
                mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            } else {
                throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
            }

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
            toolbarLayout.setTitle(mMovie.getTitle());

            backdrop = (ImageView) findViewById(R.id.backdrop);
            title = (TextView) findViewById(R.id.movie_title);
            description = (TextView) findViewById(R.id.movie_description);
            poster = (ImageView) findViewById(R.id.movie_poster);

            title.setText(mMovie.getTitle());
            description.setText(mMovie.getDescription());
            dataManager.getmPicaso().with(this)
                    .load("http://image.tmdb.org/t/p/w500" + mMovie.getPoster())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(poster);
            dataManager.getmPicaso().with(this)
                    .load("http://image.tmdb.org/t/p/w500" + mMovie.getBackdrop())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(backdrop);
        }
    }


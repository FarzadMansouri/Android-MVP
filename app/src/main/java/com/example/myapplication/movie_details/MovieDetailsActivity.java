package com.example.myapplication.movie_details;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.ListItemMovies;
import com.example.myapplication.model.CastAct;
import com.example.myapplication.movie_details.adapter.MovieDetailsAdapter;
import com.example.myapplication.remote.RetroClass;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDetailsActivity extends AppCompatActivity implements ContractMovieDetails.View {

    private MovieDetailsPresenter presenter;
    private ListItemMovies listItemMovies;
    private TextView title_movie;
    private TextView popularity;
    private TextView release_date;
    private TextView vote_count;
    private TextView overview;
    private ImageView imageView;
    private ProgressBar progressBarimage;
    private ProgressBar progressBarRecycler;
    private RecyclerView recyclerView;
    private MovieDetailsAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        listItemMovies = getIntent().getExtras().getParcelable("Movie_Item");
        presenter = new MovieDetailsPresenter(this);
        presenter.sendRequestForDetails(listItemMovies.getId(), "credits");

        title_movie = findViewById(R.id.title_movie);
        popularity = findViewById(R.id.popularity);
        release_date = findViewById(R.id.release_date);
        vote_count = findViewById(R.id.vote_count);
        imageView = findViewById(R.id.imageView);
        progressBarimage = findViewById(R.id.progress_image);
        progressBarRecycler = findViewById(R.id.progress_recycler);
        recyclerView = findViewById(R.id.cast_recycler);
        overview=findViewById(R.id.overview);

        title_movie.setText(listItemMovies.getTitle());
        overview.setText(listItemMovies.getOverview());
        popularity.setText("Popularity: " + listItemMovies.getPopularity());
        release_date.setText("Release Date: " + listItemMovies.getReleaseDate());
        vote_count.setText("Vote Count: " + listItemMovies.getVoteCount());
        Log.e(RetroClass.TAG, "onCreate overview: " + listItemMovies.getOverview());
        if (imageView != null) {
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w200"+listItemMovies.getPosterPath())
                    .centerCrop()
                    .into(imageView);

            progressBarimage.setVisibility(View.GONE);
        }


    }

    @Override
    public void showLoading() {
        progressBarRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBarRecycler.setVisibility(View.GONE);
    }

    @Override
    public void setToRecycler(List<CastAct> castActs) {
        Log.e(RetroClass.TAG, "setToRecycler: "+castActs.size());
        adapter = new MovieDetailsAdapter();
        adapter.setCastActList(castActs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter.notifyDataSetChanged();
    }
}

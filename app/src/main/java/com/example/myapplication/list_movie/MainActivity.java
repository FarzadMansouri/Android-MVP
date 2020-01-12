package com.example.myapplication.list_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.model.ListItemMovies;
import com.example.myapplication.list_movie.adapter.CategoryAdapter;
import com.example.myapplication.movie_details.MovieDetailsActivity;
import com.example.myapplication.remote.RetroClass;

import java.util.List;


public class MainActivity extends AppCompatActivity implements ContractPosterMovie.View, CategoryAdapter.MovieClickListener {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ProgressBar bar;
    private CategoryAdapter adapter;
    private List<ListItemMovies> listItemMovies;
    private PresenterPosterMovie presenterPosterMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.category_recycler);
        bar = findViewById(R.id.progress);
        presenterPosterMovie = new PresenterPosterMovie(this);
        presenterPosterMovie.sendRequestForResult();


    }


    @Override
    public void showLoading() {

        bar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {

        bar.setVisibility(View.GONE);

    }

    @Override
    public void setDataToRecycler(List<ListItemMovies> listCategories) {

        listItemMovies=listCategories;
        Log.e(RetroClass.TAG, "setDataToRecycler data size: "+listCategories.size() );

        setRecycler(listCategories);
    }

    public void setRecycler(List<ListItemMovies> categories) {

        adapter=new CategoryAdapter(this);
        adapter.setListCategories(categories);
        recyclerView.setAdapter(adapter);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);


    }

    @Override
    public void ClickListener(int position) {

 startActivity(new Intent(this, MovieDetailsActivity.class)
 .putExtra("Movie_Item",listItemMovies.get(position))
 );

    }
}

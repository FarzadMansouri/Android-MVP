package com.example.myapplication.list_movie.adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;
import com.example.myapplication.model.ListItemMovies;
import com.example.myapplication.remote.RetroClass;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<ListItemMovies> listCategories;
    private MovieClickListener movieClickListener;

    public CategoryAdapter(MovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }

    public void setListCategories(List<ListItemMovies> listCategories) {
        Log.e(RetroClass.TAG, "setListCategories adapter: " + listCategories.size());
        this.listCategories = listCategories;

    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false),movieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.ViewHolder holder, int position) {

        ListItemMovies item = listCategories.get(position);


        holder.name.setText(item.getTitle());
        holder.language.setText(item.getOriginalLanguage());
        holder.vote_count_movie.setText(String.valueOf(item.getVoteCount()));
        Glide.with(holder.itemView)
                .load("https://image.tmdb.org/t/p/w200"+item.getPosterPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.VISIBLE);
                        return false;

                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.ic_image_black_24dp).error(R.drawable.ic_image_black_24dp))
                .centerCrop()
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {

        return listCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name;
        private TextView vote_count_movie;
        private TextView vote;
        private TextView language;
        private ProgressBar progressBar;
        private MovieClickListener movieClickListener;
        public ViewHolder(@NonNull View itemView, final MovieClickListener movieClickListener) {
            super(itemView);

            this.movieClickListener=movieClickListener;
            imageView = itemView.findViewById(R.id.image_cat);
            name = itemView.findViewById(R.id.name_movie);
            vote = itemView.findViewById(R.id.vote_movie);
            language = itemView.findViewById(R.id.lan_movie);
            vote_count_movie= itemView.findViewById(R.id.vote_count_movie);
            progressBar = itemView.findViewById(R.id.progress);
           imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieClickListener.ClickListener(getAdapterPosition());
                }
            });

        }
    }

    public interface MovieClickListener{
        void ClickListener(int position);
    }

}


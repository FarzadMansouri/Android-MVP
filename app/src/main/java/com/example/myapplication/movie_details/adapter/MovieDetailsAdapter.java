package com.example.myapplication.movie_details.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.CastAct;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder> {

    private List<CastAct> castActList;

    public void setCastActList(List<CastAct> castActList) {
        this.castActList = castActList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CastAct item = castActList.get(position);
        Glide.with(holder.itemView)
                .load("https://image.tmdb.org/t/p/w200"+item.getProfilePath())
                .circleCrop()
                .into(holder.castImage);

        holder.castname.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return castActList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView castImage;
        private TextView castname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            castImage = itemView.findViewById(R.id.cast_image);
            castname = itemView.findViewById(R.id.cast_name);
        }
    }
}

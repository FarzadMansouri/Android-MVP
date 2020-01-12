package com.example.myapplication.list_movie;

import com.example.myapplication.model.ListItemMovies;

import java.util.List;

public class PresenterPosterMovie implements ContractPosterMovie.Presenter, ContractPosterMovie.Remote.FinishListener {

    private ContractPosterMovie.View viewListCategory;
    private ContractPosterMovie.Remote remoteModelCategory;

    public PresenterPosterMovie(ContractPosterMovie.View viewList) {
        this.viewListCategory = viewList;
        this.remoteModelCategory = new ModelListCategory();

    }

    @Override
    public void sendRequestForResult() {

        if (viewListCategory != null) {
            viewListCategory.showLoading();
        }

        remoteModelCategory.getResult(this);
    }

    @Override
    public void onSuccess(List<ListItemMovies> posterMovies) {

        viewListCategory.setDataToRecycler(posterMovies);

        if (viewListCategory != null) {
            viewListCategory.hideLoading();
        }

    }

    @Override
    public void onFailure(Throwable throwable) {

        if (viewListCategory != null) {
            viewListCategory.hideLoading();
        }
    }
}

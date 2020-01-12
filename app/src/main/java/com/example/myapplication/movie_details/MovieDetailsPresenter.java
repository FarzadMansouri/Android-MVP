package com.example.myapplication.movie_details;

import com.example.myapplication.model.CastAct;

import java.util.List;

public class MovieDetailsPresenter implements ContractMovieDetails.Presenter, ContractMovieDetails.Remote.OnFinishListener {

   private ContractMovieDetails.View viewDetails;
    private ContractMovieDetails.Remote remoteDetails;

    public MovieDetailsPresenter(ContractMovieDetails.View viewDetails) {
        this.viewDetails = viewDetails;
        this.remoteDetails=new MovieDetailsModel();

    }


    @Override
    public void sendRequestForDetails(int movieId,String credits) {

        remoteDetails.getListDetails(this,movieId,credits);
    }

    @Override
    public void onSuccess(List<CastAct> listItemMovies) {

        viewDetails.setToRecycler(listItemMovies);
        if(viewDetails!=null){
            viewDetails.hideLoading();
        }

    }

    @Override
    public void onFailure(Throwable t) {

        if(viewDetails!=null){
            viewDetails.hideLoading();
        }
    }
}

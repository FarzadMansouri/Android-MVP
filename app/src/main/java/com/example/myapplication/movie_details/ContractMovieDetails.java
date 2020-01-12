package com.example.myapplication.movie_details;

import com.example.myapplication.model.CastAct;

import java.util.List;

public interface ContractMovieDetails {

    interface Remote {
        interface OnFinishListener {
            void onSuccess(List<CastAct> listItemMovies);

            void onFailure(Throwable t);
        }

        void getListDetails(OnFinishListener onFinishListener,int movieId,String credits);

    }

    interface View {
        void showLoading();
        void hideLoading();
        void setToRecycler(List<CastAct> castActs);
    }

    interface Presenter {
        void sendRequestForDetails(int movieId,String credits);
    }


}

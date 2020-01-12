package com.example.myapplication.list_movie;

import com.example.myapplication.model.ListItemMovies;

import java.util.List;

public interface ContractPosterMovie {

    interface Remote {
        interface FinishListener {
            void onSuccess(List<ListItemMovies> posterMovies);
            void onFailure(Throwable throwable);
        }

        void getResult(FinishListener finishListener);

    }

    interface View {

        void showLoading();

        void hideLoading();

        void setDataToRecycler(List<ListItemMovies> listCategories);

    }

    interface Presenter {

        void sendRequestForResult();

    }


}

package com.callor.memo.db;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory  implements ViewModelProvider.Factory {


    private final MemoDataSource mDataSource;

    public ViewModelFactory(MemoDataSource mDataSource) {
        this.mDataSource = mDataSource;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MemoViewModel.class)) {
            return (T) new MemoViewModel(mDataSource);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");

    }
}

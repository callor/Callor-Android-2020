package com.callor.memo.db;

import androidx.lifecycle.ViewModel;

import com.callor.memo.domain.MemoVO;

public class MemoViewModel extends ViewModel {

    private final MemoDataSource mDataSource;

    private MemoVO memoVO;

    public MemoViewModel(MemoDataSource mDataSource) {
        this.mDataSource = mDataSource;
    }


}

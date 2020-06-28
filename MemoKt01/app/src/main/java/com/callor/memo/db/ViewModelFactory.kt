package com.callor.memo.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val mDataSource: MemoDataSource) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MemoViewModel::class.java)) {
            return MemoViewModel(mDataSource) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}

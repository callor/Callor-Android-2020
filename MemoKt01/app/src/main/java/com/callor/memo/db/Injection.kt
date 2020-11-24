package com.callor.memo.db

import android.content.Context

object Injection {


    fun provideUserDataSource(context: Context): MemoDataSource {
        val database = MemoDB.getInstance(context)
        return MemoDataSourceImpl(database!!.memoDao)
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }

}

package com.callor.memo.db

import com.callor.memo.dao.MemoDao
import com.callor.memo.domain.MemoVO

import io.reactivex.Completable
import io.reactivex.Flowable

class MemoDataSourceImpl(private val memoDao: MemoDao) : MemoDataSource {

    override val memos: Flowable<MemoVO>
        get() = memoDao.all

    override fun insertOrUpdateMemo(memoVO: MemoVO): Completable? {
        memoDao.insert(memoVO)
        return null
    }


    override fun delete(rowid: Long) {
        memoDao.delete(rowid)
    }

}

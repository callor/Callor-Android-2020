package com.callor.memo.db

import com.callor.memo.domain.MemoVO

import io.reactivex.Completable
import io.reactivex.Flowable

interface MemoDataSource {


    /**
     * Gets the user from the data source.
     *
     * @return the user from the data source.
     */
    val memos: Flowable<MemoVO>

    /**
     * Inserts the user into the data source, or, if this is an existing user, updates it.
     *
     * @param user the user to be inserted or updated.
     */
    fun insertOrUpdateMemo(memoVO: MemoVO): Completable

    /**
     * Deletes all users from the data source.
     */
    fun delete(rowid: Long)

}

package com.callor.memo.db;

import com.callor.memo.domain.MemoVO;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface MemoDataSource {


        /**
         * Gets the user from the data source.
         *
         * @return the user from the data source.
         */
        Flowable<MemoVO> getMemos();

        /**
         * Inserts the user into the data source, or, if this is an existing user, updates it.
         *
         * @param user the user to be inserted or updated.
         */
        Completable insertOrUpdateMemo(MemoVO memoVO);

        /**
         * Deletes all users from the data source.
         */
        void delete(long rowid);

}

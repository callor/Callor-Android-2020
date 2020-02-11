package com.callor.memo.db;

import com.callor.memo.dao.MemoDao;
import com.callor.memo.domain.MemoVO;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class MemoDataSourceImpl implements MemoDataSource {


    private final MemoDao memoDao;

    public MemoDataSourceImpl(MemoDao memoDao) {
        this.memoDao = memoDao;
    }

    @Override
    public Flowable<MemoVO> getMemos() {
        return memoDao.getAll();
    }

    @Override
    public Completable insertOrUpdateMemo(MemoVO memoVO) {
        memoDao.insert(memoVO);
        return null;
    }


    @Override
    public void delete(long rowid) {
        memoDao.delete(rowid);
    }

}

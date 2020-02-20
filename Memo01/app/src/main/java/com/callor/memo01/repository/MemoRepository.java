package com.callor.memo01.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.callor.memo01.db.MemoDataBase;
import com.callor.memo01.domain.MemoVO;

import java.util.List;

/*
DB 접근할때 사용할 Service 클래스
 */
public class MemoRepository {

    private MemoDao mDao;
    private LiveData<List<MemoVO>> memoList;

    public MemoRepository(Application application) {
        MemoDataBase db = MemoDataBase.getInstance(application);
        mDao = db.getMemoDao();
        memoList = mDao.selectAll();
    }

    public LiveData<List<MemoVO>> selectAll() {
        return memoList;
    }

    /* thread로 save 실행 */
    public void save(final MemoVO memoVO) {
        /*
        MemoDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.save(memoVO);
            }
        });

         */

        MemoDataBase.databaseWriteExecutor.execute(()->{
            mDao.save(memoVO);
        });

    }
    public void delete(MemoVO memoVO) {
        MemoDataBase.databaseWriteExecutor.execute(()->mDao.delete(memoVO));
    }
}

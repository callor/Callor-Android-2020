package com.biz.memo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.biz.memo.db.MemoDataBase
import com.biz.memo.domain.MemoVO

/*
DB 접근할때 사용할 Service 클래스
*/
class MemoRepository(application: Application) {
    private lateinit var mDao: MemoDao
    private  val memoList: LiveData<MutableList<MemoVO?>?>
    fun selectAll(): LiveData<MutableList<MemoVO?>?>? {
        // val memoList : LiveData<MutableList<MemoVO> = mDao.selectAll()
        // return memoList
        return mDao.selectAll()
    }

    fun insert(memoVO: MemoVO?) {
        /*
        MemoDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.save(memoVO);
            }
        });
         */
        MemoDataBase.databaseWriteExecutor.execute(Runnable { mDao.save(memoVO) })
    }

    fun delete(id: Long) {
        mDao.delete(id)
    }

    fun findById(id: Long): MemoVO? {
        return mDao.findById(id)
    }

    init {
        val db: MemoDataBase? = MemoDataBase.getInstance(application)
        if (db != null) {
            mDao = db.getMemoDao()!!// .memoDao
        }
        memoList = mDao.selectAll()!!
    }
}
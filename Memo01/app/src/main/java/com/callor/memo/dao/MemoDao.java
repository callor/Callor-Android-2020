package com.callor.memo.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.callor.memo.domain.MemoVO;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface MemoDao {

    @Query("SELECT * FROM tbl_memo")
    Flowable<MemoVO> getAll();

     @Query("SELECT * FROM tbl_memo WHERE rowid IN (:rowid)")
     List<MemoVO> loadAllByIds(int[] rowid);

     @Query("SELECT * FROM  tbl_memo WHERE m_text LIKE :m_text")
     MemoVO findByText(String m_text);

     @Insert
     void insertAll(MemoVO... memoList);

     @Insert
     void insert(MemoVO memoVO);

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     Completable insertMemo(MemoVO memoVO);

     @Delete
     void delete(long rowid);


}

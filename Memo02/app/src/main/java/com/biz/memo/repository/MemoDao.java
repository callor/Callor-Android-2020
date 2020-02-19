package com.biz.memo.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.biz.memo.domain.MemoVO;

import java.util.List;

@Dao
public interface MemoDao {

    @Query("SELECT * FROM tbl_memo")
    LiveData<List<MemoVO>> selectAll();

    @Query("SELECT * FROM tbl_memo WHERE rowid = :rowid ")
    MemoVO findByRowId(String rowid);

    @Query("SELECT * FROM tbl_memo WHERE m_text LIKE :m_text")
    LiveData<List<MemoVO>> findByText(String m_text);

    /*
    ORM 구조에서는 새로운 데이터는 insert를 수행하고
    기존 데이터는 replace를 수행하는 메서드를 공통으로 사용을 한다.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(MemoVO memoVO);

    @Update
    void update(MemoVO memoVO);

    @Query("DELETE FROM tbl_memo WHERE rowid = :rowid")
    void delete(String rowid);

    @Delete
    void delete(MemoVO post);
}
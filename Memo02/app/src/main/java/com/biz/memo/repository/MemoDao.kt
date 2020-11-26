package com.biz.memo.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.biz.memo.domain.MemoVO


@Dao
interface MemoDao {
    @Query("SELECT * FROM tbl_memo")
    fun selectAll(): LiveData<MutableList<MemoVO?>?>?

    @Query("SELECT * FROM tbl_memo WHERE rowid = :rowid ")
    fun findByRowId(rowid: String?): MemoVO?

    @Query("SELECT * FROM tbl_memo WHERE m_text LIKE :m_text")
    fun findByText(m_text: String?): LiveData<MutableList<MemoVO?>?>?

    /*
    ORM 구조에서는 새로운 데이터는 insert를 수행하고
    기존 데이터는 replace를 수행하는 메서드를 공통으로 사용을 한다.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(memoVO: MemoVO?)

    // @Update
    // open fun update(memoVO: MemoVO?)
    @Query("DELETE FROM tbl_memo WHERE rowid = :rowid")
    fun delete(rowid: String?)

    @Query("DELETE FROM tbl_memo WHERE id =:id")
    fun delete(id: Long)
    // fun delete(post: Any)

}
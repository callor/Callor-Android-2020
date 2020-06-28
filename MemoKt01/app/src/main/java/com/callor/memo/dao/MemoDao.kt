package com.callor.memo.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.callor.memo.domain.MemoVO

import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MemoDao {

    @get:Query("SELECT * FROM tbl_memo")
    val all: Flowable<MemoVO>

    @Query("SELECT * FROM tbl_memo WHERE rowid IN (:rowid)")
    fun loadAllByIds(rowid: IntArray): List<MemoVO>

    @Query("SELECT * FROM  tbl_memo WHERE m_text LIKE :m_text")
    fun findByText(m_text: String): MemoVO

    @Insert
    fun insertAll(vararg memoList: MemoVO)

    @Insert
    fun insert(memoVO: MemoVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemo(memoVO: MemoVO): Completable

    @Delete
    fun delete(rowid: Long)


}

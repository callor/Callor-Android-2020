package com.biz.memo.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_memo")
class MemoVO {
    /*
    PK 지정된 숫자형 칼럼에 auto increment 를 부여하는 속성
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long = 0

    @ColumnInfo(name = "m_date")
    var m_date: String? = null

    @ColumnInfo(name = "m_time")
    var m_time: String? = null

    @ColumnInfo
    var m_text: String? = null
}
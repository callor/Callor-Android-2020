package com.callor.memo.domain


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/*
전체 텍스트 검색 지원
앱에서 FTS(전체 텍스트 검색)를 통해 데이터베이스 정보에
매우 빠르게 액세스해야 한다면
FTS3 또는 FTS4 SQLite 확장 모듈을 사용하는 가상 테이블을 통해 항목을 지원하세요.
Room 2.1.0 이상에서 제공되는 이 기능을 사용하려면
다음 코드 스니펫에서와 같이 지정된 항목에 @Fts3 또는 @Fts4 주석을 추가하세요.
 */
@Fts4
@Entity(tableName = "tbl_memo")
class MemoVO {


    /*
    참고: FTS 지원 테이블은 항상 INTEGER 유형의 기본 키를 사용하며
    'rowid'라는 열 이름을 사용합니다.
    FTS 테이블 지원 항목에서 기본 키를 정의하는 경우
    이러한 유형 및 열 이름을 반드시 사용해야 합니다.
     */

    @PrimaryKey
    @ColumnInfo(name = "rowid")
    var id: Int = 0
        set(id) {
            field = this.id
        }

    @ColumnInfo(name = "m_date")
    var m_date: String? = null
        set(m_date) {
            field = this.m_date
        }

    @ColumnInfo(name = "m_time")
    var m_time: String? = null
        set(m_time) {
            field = this.m_time
        }

    @ColumnInfo(name = "m_text")
    var m_text: String? = null
        set(m_text) {
            field = this.m_text
        }


}

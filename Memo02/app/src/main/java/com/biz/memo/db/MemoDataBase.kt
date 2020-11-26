package com.biz.memo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.biz.memo.domain.MemoVO
import com.biz.memo.repository.MemoDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors



@Database(entities = [MemoVO::class], version = 1, exportSchema = false)
abstract class MemoDataBase : RoomDatabase() {
//    abstract val memoDao: MemoDao

     abstract fun getMemoDao(): MemoDao?

    companion object {
        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: MemoDataBase? = null
        private const val NUMBER_OF_THREADS = 4

        // 1.8 lambda 1급 함수 형식의 람다 클래스 선언
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        @Synchronized
        fun getInstance(context: Context): MemoDataBase? {
            if (INSTANCE == null) {
                // synchronized(MemoDataBase::class.java) {
                //    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                MemoDataBase::class.java, "word_database") // .addCallback(sRoomDatabaseCallback)
                                .allowMainThreadQueries()
                                .build()
                  //  }
                //}
            }
            return INSTANCE
        }
    }
}
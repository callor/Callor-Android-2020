package com.callor.memo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

import com.callor.memo.dao.MemoDao
import com.callor.memo.domain.MemoVO


@Database(version = 1, entities = [MemoVO::class])
abstract class MemoDB : RoomDatabase() {

    abstract val memoDao: MemoDao

    companion object {
        @Volatile
        private var INSTANCE: MemoDB? = null

        fun getInstance(context: Context): MemoDB? {
            if (INSTANCE == null) {
                synchronized(MemoDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                MemoDB::class.java, "Memo.dbf")
                                .addCallback(object : RoomDatabase.Callback() {

                                    /*
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // 쓰레드를 만들것
                                    Runnable r = new Runnable() {
                                        @Override
                                        public void run() {
                                            getInstance(context).getMemoDao().insert(new MemoVO);
                                        }
                                    };
                                    Thread thread = new Thread(r);
                                    thread.start();
                                }
                                */
                                })
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}

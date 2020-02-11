package com.callor.memo.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.callor.memo.dao.MemoDao;
import com.callor.memo.domain.MemoVO;


@Database(version = 1, entities = {MemoVO.class})
public abstract class MemoDB extends RoomDatabase {

    abstract public MemoDao getMemoDao();
    private static volatile MemoDB INSTANCE;

    public static MemoDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MemoDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MemoDB.class, "Memo.dbf")
                            .addCallback(new Callback() {
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
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

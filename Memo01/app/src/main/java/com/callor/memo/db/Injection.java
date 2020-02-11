package com.callor.memo.db;

import android.content.Context;

public class Injection {


        public static MemoDataSource provideUserDataSource(Context context) {
            MemoDB database = MemoDB.getInstance(context);
            return new MemoDataSourceImpl(database.getMemoDao());
        }

        public static ViewModelFactory provideViewModelFactory(Context context) {
            MemoDataSource dataSource = provideUserDataSource(context);
            return new ViewModelFactory(dataSource);
        }

}

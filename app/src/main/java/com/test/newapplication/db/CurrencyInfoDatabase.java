package com.test.newapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CurrencyInfoEntity.class}, version = 1)
public abstract class CurrencyInfoDatabase extends RoomDatabase {
    public abstract CurrencyInfoDao currencyInfoDao();

    private static volatile CurrencyInfoDatabase INSTANCE;

    public static CurrencyInfoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CurrencyInfoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CurrencyInfoDatabase.class, "currencyInfos")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static ExecutorService getExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}

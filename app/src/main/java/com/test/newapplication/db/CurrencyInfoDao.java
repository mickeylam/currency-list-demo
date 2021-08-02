package com.test.newapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CurrencyInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCurrency(CurrencyInfoEntity currencyInfoEntity);

    @Delete
    public void deleteCurrency(CurrencyInfoEntity currencyInfoEntity);

    @Query("SELECT * FROM currency")
    public List<CurrencyInfoEntity> getAllCurrencyInfos();

    @Query("SELECT * FROM currency ORDER BY CASE WHEN :isAsc = 1 THEN id END ASC, CASE WHEN :isAsc = 0 THEN id END DESC")
    public List<CurrencyInfoEntity> getCurrencyInfoListByOrder(boolean isAsc);
}

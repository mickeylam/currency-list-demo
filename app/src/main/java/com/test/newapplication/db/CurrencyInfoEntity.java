package com.test.newapplication.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "currency")
public class CurrencyInfoEntity {
    @PrimaryKey
    @NonNull
    public String id;
    public String name;
    public String symbol;

    public CurrencyInfoEntity() {

    }

    public CurrencyInfoEntity(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

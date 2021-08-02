package com.test.newapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrencyInfo implements Parcelable {
    private String id;
    private String name;
    private String symbol;

    public CurrencyInfo(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    protected CurrencyInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        symbol = in.readString();
    }

    public static final Creator<CurrencyInfo> CREATOR = new Creator<CurrencyInfo>() {
        @Override
        public CurrencyInfo createFromParcel(Parcel in) {
            return new CurrencyInfo(in);
        }

        @Override
        public CurrencyInfo[] newArray(int size) {
            return new CurrencyInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(symbol);
    }
}

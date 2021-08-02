package com.test.newapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.newapplication.db.CurrencyInfoDatabase;
import com.test.newapplication.db.CurrencyInfoEntity;
import com.test.newapplication.model.CurrencyInfo;
import com.test.newapplication.ui.main.CurrencyListFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {
    private boolean isAsc = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        fetchDataFromAssets();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.container, CurrencyListFragment.newInstance())
                    .commitNow();
        }

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortData();
            }
        });
    }

    private void fetchDataFromAssets() {
        String jsonFileString = Utils.getJsonData(this, "currencylist.json");
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<CurrencyInfo>>(){}.getType();
        List<CurrencyInfo> currencyInfoList = gson.fromJson(jsonFileString, collectionType);
        if (currencyInfoList != null) {
            for (CurrencyInfo currencyInfo: currencyInfoList) {
                CurrencyInfoDatabase.getExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        CurrencyInfoDatabase.getInstance(getApplicationContext()).currencyInfoDao().insertCurrency(
                                new CurrencyInfoEntity(currencyInfo.getId(), currencyInfo.getName(), currencyInfo.getSymbol())
                        );
                    }
                });
            }
        }
    }

    private void loadData() {
        CurrencyInfoDatabase.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<CurrencyInfoEntity> currencyInfoEntityList = CurrencyInfoDatabase.getInstance(getApplicationContext()).currencyInfoDao().getAllCurrencyInfos();
                ArrayList<CurrencyInfo> currencyInfoList = new ArrayList<>();
                for (CurrencyInfoEntity entity : currencyInfoEntityList) {
                    currencyInfoList.add(new CurrencyInfo(entity.getId(), entity.getName(), entity.getSymbol()));
                }

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("a", currencyInfoList);
                CurrencyListFragment fragment = new CurrencyListFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.currency_list_fragment, fragment).commit();
            }
        });
    }

    private void sortData() {
        isAsc = !isAsc;
        CurrencyInfoDatabase.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<CurrencyInfoEntity> currencyInfoEntityList = CurrencyInfoDatabase.getInstance(getApplicationContext()).currencyInfoDao().getCurrencyInfoListByOrder(isAsc);
                ArrayList<CurrencyInfo> currencyInfoList = new ArrayList<>();
                for (CurrencyInfoEntity entity : currencyInfoEntityList) {
                    currencyInfoList.add(new CurrencyInfo(entity.getId(), entity.getName(), entity.getSymbol()));
                }

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("a", currencyInfoList);
                CurrencyListFragment fragment = new CurrencyListFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.currency_list_fragment, fragment).commit();
            }
        });
    }
}
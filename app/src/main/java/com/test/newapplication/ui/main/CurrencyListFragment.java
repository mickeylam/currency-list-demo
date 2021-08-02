package com.test.newapplication.ui.main;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.newapplication.DemoActivity;
import com.test.newapplication.R;
import com.test.newapplication.adapter.CurrencyListRecyclerAdapter;
import com.test.newapplication.model.CurrencyInfo;

import java.util.ArrayList;
import java.util.List;

public class CurrencyListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<CurrencyInfo> currencyInfos = new ArrayList<>();
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
        }
    };

    public static CurrencyListFragment newInstance() {
        return new CurrencyListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        if (getArguments() != null) {
            currencyInfos = getArguments().getParcelableArrayList("a");
        }
        CurrencyListRecyclerAdapter adapter = new CurrencyListRecyclerAdapter(currencyInfos);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
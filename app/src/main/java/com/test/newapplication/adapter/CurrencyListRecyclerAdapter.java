package com.test.newapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.newapplication.R;
import com.test.newapplication.model.CurrencyInfo;

import java.util.List;

public class CurrencyListRecyclerAdapter extends RecyclerView.Adapter<CurrencyListRecyclerAdapter.ViewHolder>{
    List<CurrencyInfo> currencyInfos;
    private View.OnClickListener onItemClickListener;

    public CurrencyListRecyclerAdapter(List<CurrencyInfo> currencyInfos) {
        this.currencyInfos = currencyInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurrencyInfo currencyInfo = currencyInfos.get(position);
        holder.bind(currencyInfo);
    }

    @Override
    public int getItemCount() {
        return currencyInfos.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView symbol;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textView);
            name = itemView.findViewById(R.id.textView2);
            symbol = itemView.findViewById(R.id.textView3);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        public void bind(CurrencyInfo currencyInfo) {
            id.setText(currencyInfo.getId());
            name.setText(currencyInfo.getName());
            symbol.setText(currencyInfo.getSymbol());
        }
    }
}

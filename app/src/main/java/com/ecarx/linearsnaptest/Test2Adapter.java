package com.ecarx.linearsnaptest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Test2Adapter extends RecyclerView.Adapter<Test2Adapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_test2, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.viewType == 0) {
            holder.itemView.setBackgroundColor(holder.hashCode() | 0xFF000000);
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return 20 + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 0 && position < getItemCount() - 1)
            return 0;
        else
            return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        int viewType;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
        }
    }
}

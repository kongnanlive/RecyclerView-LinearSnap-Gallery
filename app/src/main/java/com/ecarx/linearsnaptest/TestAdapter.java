package com.ecarx.linearsnaptest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_test, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.viewType == 0) {
            holder.item.setBackgroundColor(holder.hashCode() | 0xFF000000);
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

        View item;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            item = itemView.findViewById(R.id.item);
        }
    }
}

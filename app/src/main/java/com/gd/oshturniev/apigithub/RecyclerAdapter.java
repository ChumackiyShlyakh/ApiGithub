package com.gd.oshturniev.apigithub;

import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder recyclerHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView card_view;
        TextView tv_item;
        private ItemClickListener itemClickListener;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            card_view = (CardView) itemView.findViewById(R.id.card_view);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }
    }
}

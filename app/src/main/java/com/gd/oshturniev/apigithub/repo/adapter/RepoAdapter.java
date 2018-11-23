package com.gd.oshturniev.apigithub.repo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

//import com.gd.oshturniev.apigithub.ItemClickListener;
import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RecyclerHolder> {

//    private UserViewModel viewModel;
    private List<UserResponse> users;

    public RepoAdapter(){
        this.users = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item,
                new FrameLayout(viewGroup.getContext()), false);
        return new RecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        UserResponse dataModel = users.get(position);
//        holder.setViewModel(new DataItemViewModel(dataModel));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView card_view;
        TextView tv_item;
//        private ItemClickListener itemClickListener;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            card_view = (CardView) itemView.findViewById(R.id.card_view);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }
    }
}

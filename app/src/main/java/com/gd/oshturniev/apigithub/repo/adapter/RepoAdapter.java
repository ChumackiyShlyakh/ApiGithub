package com.gd.oshturniev.apigithub.repo.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.databinding.FragmentGitBinding;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RecyclerHolder> {

    private List<UserResponse> userResponse;
//    private final FragmentGitBinding binding;

    public RepoAdapter(){ // FragmentGitBinding binding, UserResponse userResponse
//        this.binding = binding;
        this.userResponse = new ArrayList<>();
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
        UserResponse user = userResponse.get(position);
//        holder.setIsRecyclable(new UserViewModel(user));
    }

    public void updateData(@Nullable List<UserResponse> data) {
        if (data == null || data.isEmpty()) {
            this.userResponse.clear();
        } else {
            this.userResponse.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userResponse.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

//        ItemDataBinding binding;

//        RecyclerHolder(View itemView) {
//            super(itemView);
//            bind();
//        }

//       void bind() {
//            if (binding == null) {
//                binding = DataBindingUtil.bind(itemView);
//            }
//        }

//        void unbind() {
//            if (binding != null) {
//                binding.unbind();
//            }
//        }
//
//        void setViewModel(DataItemViewModel viewModel) {
//            if (binding != null) {
//                binding.setViewModel(viewModel);
//            }}

        @Override
        public void onClick(View v) {
//            this.itemClickListener.onItemClick(v, getLayoutPosition());
 }
        }
    }

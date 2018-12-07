package com.gd.oshturniev.apigithub.repo.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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
import com.gd.oshturniev.apigithub.repo.viewmodel.RepoViewModel;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RecyclerHolder> {

    private List<UserResponse> userResponse;

    public RepoAdapter(List<UserResponse> response) {
        userResponse = response;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.fragment_git, viewGroup, false);

        return new RecyclerHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder customViewHolder, int i) {
        ViewDataBinding viewDataBinding = customViewHolder.getViewDataBinding();

        viewDataBinding.setVariable(BR.repo, userResponse.get(i)); // se.jayway.databinding.BR.mymodel
    }

    @Override
    public int getItemCount() {
        return (null != userResponse ? userResponse.size() : 0);
    }

//    @Override
//    public RepoAdapter.RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.card_item, parent, false);
//        return new RecyclerHolder(v);
//    }
//
////    @Override
////    public void onBindViewHolder(RecyclerBindingAdapter.BindingHolder holder, int position) {
////        final T item = items.get(position);
////        holder.getBinding().getRoot();
////        holder.getBinding().setVariable(variableId, item);
////    }
//
//
//    public RepoAdapter(UserResponse userResponse) { // FragmentGitBinding binding, UserResponse userResponse
////        this.binding = binding;
//        this.userResponse = new ArrayList<>();
//        this.userResponse.add(userResponse);
//
//    }
//
////        @NonNull
////    @Override
////    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
////        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item,
////                new FrameLayout(viewGroup.getContext()), false);
////        return new RecyclerHolder(itemView);
////    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
//        UserResponse user = userResponse.get(position);
//        holder.getBinding().getRoot();
////        holder.setViewModel(new RepoViewModel(user));
////        holder.bind(user);
//    }
//
////    public void updateData(@Nullable List<UserResponse> data) {
////        if (data == null || data.isEmpty()) {
////            this.userResponse.clear();
////        } else {
////            this.userResponse.addAll(data);
////        }
////        notifyDataSetChanged();
////    }
//
//    @Override
//    public int getItemCount() {
//        return userResponse.size();
//    }



    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

//        public RecyclerHolder(@NonNull View itemView) {
//            super(itemView);
//            binding = DataBindingUtil.bind(itemView);
//        }
//
//        public ViewDataBinding getBinding() {
//            return binding;
//        }
//    }

        public RecyclerHolder( ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());

            binding = viewDataBinding;
            binding.executePendingBindings();
        }

        public ViewDataBinding getViewDataBinding() {
            return binding;
        }
}}

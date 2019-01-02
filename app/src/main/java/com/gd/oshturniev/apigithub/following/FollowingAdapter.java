package com.gd.oshturniev.apigithub.following;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.databinding.ItemRepoBinding;
import com.gd.oshturniev.apigithub.repo.adapter.RepoAdapter;
import com.gd.oshturniev.apigithub.repo.viewmodel.DataItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.RecyclerHolder> {

    private List<FollowingResponse> followingResponses = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public FollowingAdapter() {
    }

    public void setFollowingResponse(List<FollowingResponse> followingResponses) {
        this.followingResponses = followingResponses;
        notifyDataSetChanged();
    }

    @Override
    public FollowingAdapter.RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ItemRepoBinding itemRepoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_repo, viewGroup, false);
        return new FollowingAdapter.RecyclerHolder(itemRepoBinding);
    }

    @Override
    public void onBindViewHolder(FollowingAdapter.RecyclerHolder holder, int i) {
        DataItemViewModel dataItemViewModel = new DataItemViewModel(followingResponses.get(i));
        holder.itemRepoBinding.setRepo(dataItemViewModel);
        holder.itemRepoBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return followingResponses != null ? followingResponses.size() : 0;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private ItemRepoBinding itemRepoBinding;

        public RecyclerHolder(@NonNull ItemRepoBinding itemRepoBinding) {
            super(itemRepoBinding.getRoot());
            this.itemRepoBinding = itemRepoBinding;
        }
    }
}

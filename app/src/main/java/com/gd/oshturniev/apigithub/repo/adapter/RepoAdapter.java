package com.gd.oshturniev.apigithub.repo.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.databinding.ItemRepoBinding;
import com.gd.oshturniev.apigithub.repo.viewmodel.DataItemViewModel;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RecyclerHolder> {

    private List<ReposResponse> repoResponse = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public RepoAdapter() {
    }

    public RepoAdapter(List<ReposResponse> repoResponse) {
        this.repoResponse = repoResponse;
    }

    public void setRepoResponse(List<ReposResponse> repoResponse) {
        this.repoResponse = repoResponse;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ItemRepoBinding itemRepoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_repo, viewGroup, false);
        return new RecyclerHolder(itemRepoBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int i) {
        DataItemViewModel dataItemViewModel = new DataItemViewModel(repoResponse.get(i));
        holder.itemRepoBinding.setRepo(dataItemViewModel);
        holder.itemRepoBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return repoResponse != null ? repoResponse.size() : 0;
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private ItemRepoBinding itemRepoBinding;

        public RecyclerHolder(@NonNull ItemRepoBinding itemRepoBinding) {
            super(itemRepoBinding.getRoot());
            this.itemRepoBinding = itemRepoBinding;
        }
    }
}

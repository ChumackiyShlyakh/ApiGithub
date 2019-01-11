package com.gd.oshturniev.apigithub.dagger;

import com.gd.oshturniev.apigithub.core.ui.MainActivity;
import com.gd.oshturniev.apigithub.following.fragment.FollowingFragment;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;

import dagger.Component;

@Component
public interface GitHubComponent {

    MainActivity getMainActivity();

    GitFragment getGitFragment();
    FollowingFragment getFollowingFragment();
}

package com.si.reddit.services;

import java.util.List;

import com.si.reddit.entities.UserFollowSubreddit;

public interface UserFollowSubredditService {
	public UserFollowSubreddit create(UserFollowSubreddit userFollowSubreddit);
	public void remove(UserFollowSubreddit userFollowSubreddit);
	public UserFollowSubreddit searchByIds(String dni, Long id);
	public List<UserFollowSubreddit> searchAll();
}
package com.si.reddit.services;

import java.util.List;

import com.si.reddit.entities.User;
import com.si.reddit.entities.Subreddit;
import com.si.reddit.entities.UserFollowSubreddit;

public interface UserFollowSubredditService {
	public UserFollowSubreddit create(UserFollowSubreddit userFollowSubreddit);
	public void remove(UserFollowSubreddit userFollowSubreddit);
	public UserFollowSubreddit searchByIds(String DNI, Long id);
	public List<UserFollowSubreddit> searchAll();
}
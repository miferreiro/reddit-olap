package com.si.reddit.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.si.reddit.entities.User;
import com.si.reddit.entities.Subreddit;
import com.si.reddit.entities.UserFollowSubreddit;
import com.si.reddit.entities.UserFollowSubredditID;

public interface UserFollowSubredditDAO extends JpaRepository<UserFollowSubreddit,UserFollowSubredditID>{
	List<UserFollowSubreddit> findByUserDNI(String dni);
	List<UserFollowSubreddit> findBySubredditId(Long id);
	List<UserFollowSubreddit> findBySubredditName(String name);
	List<UserFollowSubreddit> findBySubredditContaining(Subreddit subreddit);
}

package com.si.reddit.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.si.reddit.entities.Subreddit;

public interface SubredditDAO extends JpaRepository<Subreddit, Long>{
	List<Subreddit> findByNameContaining(String name);
}

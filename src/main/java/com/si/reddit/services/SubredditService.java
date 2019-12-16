package com.si.reddit.services;

import java.util.List;
import com.si.reddit.entities.Subreddit;

public interface SubredditService {
	public Subreddit create(Subreddit subreddit);
	public Subreddit edit(Subreddit subreddit);
	public void remove(Subreddit subreddit);
	public Subreddit searchById(Long id);
	public List<Subreddit> searchAll();
	public List<Subreddit> searchByName(String name);
	public boolean existsById(Long id);
}

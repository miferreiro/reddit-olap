package com.si.reddit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.si.reddit.daos.SubredditDAO;
import com.si.reddit.entities.Subreddit;

@Service
public class SubredditServiceImpl implements SubredditService {
	@Autowired
	SubredditDAO dao;

	@Override
	@Transactional
	public Subreddit create(Subreddit subreddit) {
		return dao.save(subreddit);
	}

	@Override
	@Transactional
	public Subreddit edit(Subreddit subreddit) {
		return dao.save(subreddit);
	}

	@Override
	@Transactional
	public void remove(Subreddit subreddit) {
		dao.delete(subreddit);
	}

	@Override
	@Transactional(readOnly = true)
	public Subreddit searchById(Long id) {
		return dao.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subreddit> searchAll() {
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subreddit> searchByName(String pattern) {
		return dao.findByNameContaining(pattern);
	}
}

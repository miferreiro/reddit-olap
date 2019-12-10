package com.si.reddit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.si.reddit.daos.UserDAO;
import com.si.reddit.daos.SubredditDAO;
import com.si.reddit.daos.UserFollowSubredditDAO;
import com.si.reddit.entities.UserFollowSubreddit;
import com.si.reddit.entities.UserFollowSubredditID;

@Service
public class UserFollowSubredditServiceImpl implements UserFollowSubredditService {
	@Autowired
	UserFollowSubredditDAO dao;

	@Override
	@Transactional
	public UserFollowSubreddit create(UserFollowSubreddit userFollowSubreddit) {
		return dao.save(userFollowSubreddit);
	}

	@Override
	@Transactional
	public void remove(UserFollowSubreddit userFollowSubreddit) {
		dao.delete(userFollowSubreddit);
	}

	@Override
	@Transactional(readOnly = true)
	public UserFollowSubreddit searchByIds(String DNI, Long id) {
		return dao.getOne(new UserFollowSubredditID(DNI,id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserFollowSubreddit> searchAll() {
		return dao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<UserFollowSubreddit> searchByDNIUser(String user) {
		return dao.findByUserDNI(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<UserFollowSubreddit> searchByIdSubreddit(Long id) {
		return dao.findBySubredditId(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<UserFollowSubreddit> searchByNameSubreddit(String name) {
		return dao.findBySubredditName(name);
	}
}

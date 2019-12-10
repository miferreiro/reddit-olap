package com.si.reddit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.si.reddit.daos.UserDAO;
import com.si.reddit.entities.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO dao;

	@Override
	@Transactional
	public User create(User user) {
		return dao.save(user);
	}

	@Override
	@Transactional
	public User edit(User user) {
		return dao.save(user);
	}

	@Override
	@Transactional
	public void remove(User user) {
		dao.delete(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User searchByDNI(String dni) {
		return dao.getOne(dni);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> searchAll() {
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> searchByName(String name) {
		return dao.findByName(name);
	}
}

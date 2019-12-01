package com.si.reddit.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.si.reddit.entities.User;

public interface UserDAO extends JpaRepository<User, String>{
	List<User> findByNameContaining(String name);
}

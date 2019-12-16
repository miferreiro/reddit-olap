package com.si.reddit.services;

import java.util.List;
import com.si.reddit.entities.User;

public interface UserService {
	public User create(User user);
	public User edit(User user);
	public void remove(User user);
	public User searchByDNI(String dni);
	public List<User> searchAll();
	public List<User> searchByName(String name);
	public boolean existsByDNI(String dni);
}

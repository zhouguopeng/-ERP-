package com.dao;

import com.model.User;

public interface UserDao {
	public User findByUserId(String id);
}

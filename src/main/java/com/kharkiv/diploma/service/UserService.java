package com.kharkiv.diploma.service;

import java.util.List;

import com.kharkiv.diploma.dto.user.User;

public interface UserService{

	List<User> getAllUsers();

	User getUserById(Integer id);

	User getUserByUsername(String username);

	void deleteUser(User user);

	void deleteUserById(Integer id);

	void deleteUserByUsername(String username);

	User addUser(User user);

	User updateUser(User user);

}

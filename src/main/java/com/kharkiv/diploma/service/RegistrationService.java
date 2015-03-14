package com.kharkiv.diploma.service;

import com.kharkiv.diploma.dto.user.User;

public interface RegistrationService {

	boolean isExistentUser(String username);

	void createNewUser(User user);
}

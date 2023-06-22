package com.work.demo.service;

import com.work.demo.Model.UserContacts;
import com.work.demo.Model.UserInfo;

public interface UserService {

	void addUserContact(UserContacts uc);

	UserContacts getUserContact(int id);
	
	void deleteUser(int id);
	
	void updateUser(UserContacts uc);
}

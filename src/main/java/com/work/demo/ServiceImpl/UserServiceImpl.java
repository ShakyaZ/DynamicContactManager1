package com.work.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.work.demo.Model.UserContacts;
import com.work.demo.Model.UserInfo;
import com.work.demo.Repository.ContactsRepository;
import com.work.demo.Repository.UserRepository;
import com.work.demo.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private ContactsRepository CR;
	
	@Autowired
	private UserRepository UR;

	@Override
	public void addUserContact(UserContacts uc) {
		CR.save(uc);

	}

	@Override
	public UserContacts getUserContact(int id) {
	 UserContacts u = 	CR.getById(id);
		return u;
	}


	@Override
	public void deleteUser(int id) {
		CR.deleteById(id);
	}


	@Override
	public void updateUser(UserContacts uc) {
		CR.save(uc);
		
	}

}

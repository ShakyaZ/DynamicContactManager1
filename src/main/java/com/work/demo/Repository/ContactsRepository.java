package com.work.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.demo.Model.UserContacts;

public interface ContactsRepository extends JpaRepository<UserContacts, Integer> {

}

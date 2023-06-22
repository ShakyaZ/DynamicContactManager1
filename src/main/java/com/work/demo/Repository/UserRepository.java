package com.work.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.demo.Model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer>  {
 UserInfo findByUsernameAndPassword(String un, String ps);
}

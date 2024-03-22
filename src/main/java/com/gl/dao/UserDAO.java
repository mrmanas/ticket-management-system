package com.gl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.bean.User;

public interface UserDAO extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
    
}

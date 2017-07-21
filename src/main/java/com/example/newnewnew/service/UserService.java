package com.example.newnewnew.service;


import com.example.newnewnew.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void saveUserUpdate(User user);




}

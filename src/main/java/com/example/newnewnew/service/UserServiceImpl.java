package com.example.newnewnew.service;

import java.util.Arrays;
import java.util.HashSet;

import com.example.newnewnew.model.Role;
import com.example.newnewnew.model.User;
import com.example.newnewnew.repository.RoleRepository;
import com.example.newnewnew.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



	@Override
	public User findUserByEmail(String email) {


		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setConfirmPassword(bCryptPasswordEncoder.encode(user.getConfirmPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public void saveUserUpdate(User user) {
		userRepository.save(user);
	}


}

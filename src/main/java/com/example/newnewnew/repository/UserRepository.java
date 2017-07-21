package com.example.newnewnew.repository;

import com.example.newnewnew.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
}

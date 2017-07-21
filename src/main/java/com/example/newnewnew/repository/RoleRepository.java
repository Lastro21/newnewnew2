package com.example.newnewnew.repository;

import com.example.newnewnew.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}

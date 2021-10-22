package com.yash.ppm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ppm.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}

package com.org.Item.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.org.Item.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable); 

}
	
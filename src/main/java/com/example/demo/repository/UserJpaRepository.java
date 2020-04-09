package com.example.demo.repository;

import com.example.demo.entity.UserJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpo, String> {

	
}

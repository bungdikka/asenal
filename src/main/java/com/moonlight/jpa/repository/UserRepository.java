package com.moonlight.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moonlight.jpa.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}

package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> { }

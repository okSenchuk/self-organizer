package com.sendev.selforganizer.repository;

import com.sendev.selforganizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

package com.pbl5.autoattendance.repository;

import com.pbl5.autoattendance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

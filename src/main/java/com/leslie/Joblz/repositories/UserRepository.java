package com.leslie.Joblz.repositories;

import com.leslie.Joblz.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

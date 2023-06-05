package com.rails.railway.repository;

import com.rails.railway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u WHERE u.username=?1")
    User findByUsername(String username);
}

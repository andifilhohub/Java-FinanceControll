package com.financeapi.repository;

import com.financeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    

    User findByEmail(String email);
    
}

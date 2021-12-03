package com.officeplanner.officeplanner.Dao;

import com.officeplanner.officeplanner.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
    public User findByResetPasswordToken(String token);

    @Query("SELECT p FROM User p WHERE CONCAT(p.email,' ', p.firstName, ' ', p.lastName, ' ', p.contact) LIKE %?1%" )
    public List<User> search(String keyword);

    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts,String email);

}
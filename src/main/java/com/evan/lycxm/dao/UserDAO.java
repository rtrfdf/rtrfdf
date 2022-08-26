package com.evan.lycxm.dao;

import com.evan.lycxm.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evan
 * @date 2019/4
 */
public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);
}

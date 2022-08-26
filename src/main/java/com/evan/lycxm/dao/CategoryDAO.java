package com.evan.lycxm.dao;

import com.evan.lycxm.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evan
 * @date 2019/4
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {

}

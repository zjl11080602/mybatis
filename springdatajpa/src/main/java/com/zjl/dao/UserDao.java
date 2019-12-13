package com.zjl.dao;

import com.zjl.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 朱俊磊
 * time:2019/12/14  0:59
 * version 1.0
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
}

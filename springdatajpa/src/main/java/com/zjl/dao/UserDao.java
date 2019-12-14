package com.zjl.dao;

import com.zjl.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 朱俊磊
 * time:2019/12/14  0:59
 * version 1.0
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    @Query("from  User where username= ?1 ")
    List<User> findAll(String username);
    @Modifying
    @Query("update  User u set u.username=:username where u.uid= :uid ")
    void updateById(@Param("username") String username,@Param("uid") String uid);

    @Modifying
    @Query("delete from  User  where uid= ?1")
    void deleteById(String uid);

    @Query(value = "select * from user",nativeQuery = true)
    List<User> findAllBySql();

    List<User> findByUsername(String username);
}

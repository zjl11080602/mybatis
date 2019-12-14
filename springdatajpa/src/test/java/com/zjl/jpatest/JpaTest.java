package com.zjl.jpatest;

import com.zjl.dao.UserDao;
import com.zjl.domain.User;
import com.zjl.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by 朱俊磊
 * time:2019/12/14  0:44
 * version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        System.out.println(users.size());
    }
    @Test
    public void testFindOne(){
        Optional<User> optionalUser = userDao.findById("1112");
        System.out.println(optionalUser.get().getPassword());
    }

    @Test
    public void testGetOne(){
        User user = userDao.getOne("1112");
        System.out.println(user);
    }

    @Test
    @Transactional
    @Commit
    public void testSave(){
        User user = new User();
        user.setUid("4028fef46f02f4c6016f02f4ca8d0000");
        user.setUsername("23456223334");
        user.setPassword("1234565678");
        userService.save(user);
        System.out.println(user);
    }

    @Test
    @Transactional
    @Commit
    public void testSaveWithNoId(){
        User user = new User();
        user.setUsername("23456");
        user.setPassword("3333");
        userDao.save(user);
        System.out.println(user);
    }

    @Test
    @Transactional
    @Commit
    public void testDelete(){
        userDao.deleteById("1112");
    }

    @Test
    public void testCount(){
        long count = userDao.count();
        System.out.println(count);
    }

    @Test
    public void testExist(){
        boolean b = userDao.existsById("1112");
        System.out.println(b);
    }
    @Test
    public void testQuery(){
        List<User> userList = userDao.findAll("张三");
        System.out.println(userList.size());
    }

    @Test
    @Transactional
    @Commit
    public void testUpdate(){
         userDao.updateById("李四","1113");
        User user = userDao.getOne("1113");
        System.out.println(user.getUsername());
    }
    @Test
    @Transactional
    @Commit
    public void testDeleteUser(){
        userDao.deleteById("1113");
    }
    @Test
    public void findBySQL(){
        List<User> allBySql = userDao.findAllBySql();
        System.out.println(allBySql.size());
    }

    @Test
    public void testByMethodName(){
        List<User> allBySql = userDao.findByUsername("张三");
        System.out.println(allBySql.size());
    }
}

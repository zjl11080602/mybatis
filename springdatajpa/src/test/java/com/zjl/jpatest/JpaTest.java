package com.zjl.jpatest;

import com.zjl.dao.UserDao;
import com.zjl.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        System.out.println(users.size());
    }
    @Test
    public void testFindOne(){
    }

}

package com.zjl.jpatest;

import com.zjl.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void testFind(){
        userDao.findOne("137b9be51bf14269ba304bd64e7eb02c");
    }

}

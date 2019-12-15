package com.zjl.jpatest;

import com.zjl.dao.OrdersDao;
import com.zjl.dao.RoleDao;
import com.zjl.dao.UserDao;
import com.zjl.domain.Orders;
import com.zjl.domain.Role;
import com.zjl.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneTOmanyTest {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    @Commit
    public void testOneToMany() {
        //创建一个用户
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123");
        //创建两个订单
        Orders orders1 = new Orders();
        orders1.setName("手机");

        Orders orders2 = new Orders();
        orders2.setName("电脑");
        //用户关联订单，由用户维护外键，由于由一方去维护外键的话，那么会产生多余的sql语句，所以需要一方放弃外键的维护
        //如果配置了关联关系，而且一方也没有放弃关联关系的话，那么会产生多余的sql
        user.getOrdersSet().add(orders1);
        user.getOrdersSet().add(orders2);
        //订单关联用户，由订单维护外键
        orders1.setUser(user);
        orders2.setUser(user);
        userDao.save(user);
        ordersDao.save(orders1);
        ordersDao.save(orders2);

    }

    /**
     * 测试多对多
     */
    @Test
    @Transactional
    @Commit
    public void testManyToMany() {
        //创建一个用户
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123");

        User user1 = new User();
        user1.setUsername("李武");
        user1.setPassword("1234");
        //创建两个订单
        Role role = new Role();
        role.setRoleName("经理");

        Role role1 = new Role();
        role1.setRoleName("工程师");
        //用户关联订单，由用户维护外键，由于由一方去维护外键的话，那么会产生多余的sql语句，所以需要一方放弃外键的维护
        //如果配置了关联关系，而且一方也没有放弃关联关系的话，那么会产生多余的sql
        user.getRoleSet().add(role1);
        user.getRoleSet().add(role);
        //订单关联用户，由订单维护外键
        role.getUserSet().add(user1);
        role.getUserSet().add(user);
        userDao.save(user);
        userDao.save(user1);
        roleDao.save(role);
        roleDao.save(role1);

    }

    @Test
    @Transactional
    @Commit
    public void testOneToManyCasecade() {
        //创建一个用户
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123");
        //创建两个订单
        Orders orders1 = new Orders();
        orders1.setName("手机");

        Orders orders2 = new Orders();
        orders2.setName("电脑");
        //用户关联订单，由用户维护外键，由于由一方去维护外键的话，那么会产生多余的sql语句，所以需要一方放弃外键的维护
        //如果配置了关联关系，而且一方也没有放弃关联关系的话，那么会产生多余的sql
//        user.getOrdersSet().add(orders1);
//        user.getOrdersSet().add(orders2);
        //订单关联用户，由订单维护外键
        orders1.setUser(user);
        orders2.setUser(user);
//        userDao.save(user);
        ordersDao.save(orders1);
        ordersDao.save(orders2);

    }
    //测试级联的删除，关于级联这个如果级联方放弃了外键维护，是不会更新外键的，所以存储和删除会出问题
    @Test
    @Transactional
    @Commit
    public void testOneToManyCasecadeDelete() {
        ordersDao.deleteById("4028fef46f094f25016f094f2bd70002");

    }
    //测试懒加载的问题（这个是关联对象的加载方式，hibernate和jap都有）
    @Test
    @Transactional
    @Commit
    public void testLazyLoad() {
        Optional<User> byId = userDao.findById("4028fef46f08ed06016f08ed0d8c0000");
        User role = byId.get();
        Set<Role> userSet = role.getRoleSet();
        System.out.println(userSet.size());
    }
    //测试懒加载的问题（这个是关联对象的加载方式，hibernate和jap都有）
    @Test
    @Transactional
    @Commit
    public void testOneToManyLazyLoad() {
        Optional<User> byId = userDao.findById("4028fef46f096ef0016f096ef5ee0001");
        User user = byId.get();
        System.out.println(user.getOrdersSet().size());
    }
    //测试懒加载的问题（这个是关联对象的加载方式，hibernate和jap都有）
    @Test
    @Transactional
    @Commit
    public void testOneToManyLazyLoad1() {
        Optional<Orders> byId = ordersDao.findById("4028fef46f096ef0016f096ef5fc0002");
        Orders orders = byId.get();
        System.out.println(orders.getUser());
    }
}

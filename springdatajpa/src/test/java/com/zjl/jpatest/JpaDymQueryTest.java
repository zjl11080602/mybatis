package com.zjl.jpatest;

import com.zjl.dao.UserDao;
import com.zjl.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaDymQueryTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void testFindOne(){
        Specification<User>  userSpecification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> username = root.get("username");
                Path<Object> password = root.get("password");
                Predicate predicate = criteriaBuilder.equal(username, "23456");
                Predicate predicate1 = criteriaBuilder.equal(password, "3333");
                Predicate and = criteriaBuilder.and(predicate, predicate1);
                return and;
            }
        };
        Optional<User> one = userDao.findOne(userSpecification);
        System.out.println(one.get());
    }

    @Test
    public void testFindLikeQuery(){
        Specification<User>  userSpecification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> username = root.get("username");
                Predicate predicate = criteriaBuilder.like(username.as(String.class), "张%");
                return predicate;
            }
        };
        List<User> all = userDao.findAll(userSpecification);
        System.out.println(all.size());
    }
    @Test
    public void testFindByPage(){
        Specification<User>  userSpecification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> username = root.get("username");
                Predicate predicate = criteriaBuilder.like(username.as(String.class), "张%");
                return predicate;
            }
        };
        //分页
        Pageable pageable = PageRequest.of(1,2);
        Page<User> page = userDao.findAll(userSpecification, pageable);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
    }
    @Test
    public void testFindByOrder(){
        Specification<User>  userSpecification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> username = root.get("username");
                Predicate predicate = criteriaBuilder.like(username.as(String.class), "张%");
                return predicate;
            }
        };
        //分页
        Sort sort = Sort.by(Sort.Direction.DESC,"username");
        List<User> userDaoAll = userDao.findAll(userSpecification, sort);
        System.out.println(userDaoAll);
    }
}

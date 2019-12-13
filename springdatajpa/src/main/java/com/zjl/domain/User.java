package com.zjl.domain;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 朱俊磊
 * time:2019/12/14  0:48
 * version 1.0
 */
@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(generator = "zjl")
    @GenericGenerator(name = "zjl",strategy = "uuid")
    @Column(name = "uid")
    private String uid;
    @Column(name = "username")
    private  String username;
    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="telephone")
    private String telephone;
    @Column(name="birthday")
    private Date birthday;
    @Column(name="state")
    private Integer state;
    @Column(name="sex")
    private String sex;
    @Column(name="code")
    private String code;

}

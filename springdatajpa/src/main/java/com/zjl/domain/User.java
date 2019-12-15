package com.zjl.domain;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 朱俊磊
 * time:2019/12/14  0:48
 * version 1.0
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(generator = "zjl")
    @GenericGenerator(name = "zjl",strategy = "uuid")
    @Column(name = "id")
    private String uid;
    @Column(name = "userName")
    private  String username;
    @Column(name="password")
    private String password;
    @Column(name="state")
    private Integer state;
    @OneToMany(targetEntity = Orders.class,mappedBy = "user",cascade = CascadeType.ALL)//关联对象的实体类
    private Set<Orders> ordersSet = new HashSet<>();
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",joinColumns = @JoinColumn(
            name = "sys_user_id",referencedColumnName = "id"
    ),inverseJoinColumns =@JoinColumn(name="sys_role_id",referencedColumnName = "roleId"))
    private Set<Role> roleSet = new HashSet<>();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}

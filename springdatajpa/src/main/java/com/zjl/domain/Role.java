package com.zjl.domain;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
public class Role {
    @Column(name="roleName")
    private String roleName;
    @Column(name="roleState")
    private Integer roleState;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长
    @Column(name="roleId")
    private Integer roleId;
    @ManyToMany(targetEntity = User.class,mappedBy = "roleSet")
    private Set<User> userSet = new HashSet<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleState() {
        return roleState;
    }

    public void setRoleState(Integer roleState) {
        this.roleState = roleState;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}

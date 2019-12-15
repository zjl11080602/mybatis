package com.zjl.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(generator = "zjl")
    @GenericGenerator(name="zjl",strategy = "uuid")
    @Column(name="oid")
    private String oid;
    @Column(name="price")
    private Double price;
    @Column(name="name")
    private String name;
    /**
     * 这个有两个步骤一个是配置实体类的关联关系
     * 配置外键
     */
    @ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="uid",referencedColumnName = "id")
    private  User user;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

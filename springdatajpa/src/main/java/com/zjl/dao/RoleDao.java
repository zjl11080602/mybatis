package com.zjl.dao;

import com.zjl.domain.Orders;
import com.zjl.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends JpaRepository<Role,String>, JpaSpecificationExecutor<Role> {
}

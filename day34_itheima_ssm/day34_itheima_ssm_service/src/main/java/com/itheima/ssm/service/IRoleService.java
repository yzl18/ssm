package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll(int pageNum, int pageSize) throws Exception;

    void save(Role role) throws Exception;

    Role findByRoleId(String roleId) throws Exception;

    List<Permission> findOthersPermission(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] ids) throws Exception;
}

package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findByRoleId(String roleId) throws Exception {
        return roleDao.findByRoleId(roleId);
    }

    @Override
    public List<Permission> findOthersPermission(String roleId) throws Exception {
        return roleDao.findOthersPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}

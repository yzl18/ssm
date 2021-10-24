package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo =null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //这里的user继承了UserDetails,如果配置文件没有加密，则密码需加{noop}，最后一个参数是角色描述
        //User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority());
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatusBoolean(),true,true,true,getAuthority(userInfo));
        return user;
    }

    //作用就是返回一个集合,集合里装的是集合描述
    public List<SimpleGrantedAuthority> getAuthority(UserInfo userInfo){
        List<SimpleGrantedAuthority> list= new ArrayList<>();
        List<Role> roles = userInfo.getRoles();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);

        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception{
        //对用户密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception{
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String roleId : ids) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void updatePassword(String username,String newPassword) throws Exception {
        String encode = bCryptPasswordEncoder.encode(newPassword);
        userDao.updatePassword(username,encode);
    }
}

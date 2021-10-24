package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    @RolesAllowed("TEST")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1")Integer pageNum,@RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> list = roleService.findAll(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='zhang'")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findByRoleId(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询出哪个角色需要添加权限
        Role role=roleService.findByRoleId(roleId);
        ////根据roleId查询出这个角色可以添加哪些权限
        List<Permission> permissionList=roleService.findOthersPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    @PreAuthorize("authentication.principal.username=='zhang'")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] ids) throws Exception {
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll";
    }
}

package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1")Integer pageNum,@RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list=userService.findAll(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='zhang'")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);

        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user=userService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据userId查询出哪个用户需要添加角色
        UserInfo userInfo = userService.findById(userId);
        //根据userId查询出这个用户可以添加哪些角色
        List<Role> roleList=userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    @PreAuthorize("authentication.principal.username=='zhang'")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] ids) throws Exception {
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll";
    }

    @GetMapping("/update")
    public void update(@RequestParam(name = "username",required = true)String username,@RequestParam(name = "password",required = true)String password) throws Exception {
        userService.updatePassword(username,password);
    }
}

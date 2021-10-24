package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1")Integer pageNum, @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> list = permissionService.findAll(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='zhang'")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll";
    }
}

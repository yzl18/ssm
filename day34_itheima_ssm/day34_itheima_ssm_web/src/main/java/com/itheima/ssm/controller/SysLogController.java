package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.apache.log4j.net.SyslogAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1")Integer pageNum, @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<SysLog> sysLogList= sysLogService.findAll(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}

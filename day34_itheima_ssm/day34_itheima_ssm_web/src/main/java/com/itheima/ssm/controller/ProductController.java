package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")//开启权限控制，只有具有ADMIN角色的用户才能访问该方法
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1")Integer pageNum,@RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findAll(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(productList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }


    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='zhang'")
    public String save(Product product) throws Exception {
        product.getDepartureTime();
        productService.save(product);
        return "redirect:findAll";
    }
}

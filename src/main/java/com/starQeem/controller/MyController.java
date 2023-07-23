package com.starQeem.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Date: 2023/5/18 10:44
 * @author: Qeem
 */
@Controller
public class MyController {
    @GetMapping("/user/index")
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index";
    }

    @GetMapping("/user/add")
    public String add() {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        return "user/add";
    }

    @GetMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            subject.login(token);  //执行登录的方法,如果没有异常说明ok了
            return "redirect:/user/index";
        }catch (UnknownAccountException e){ //用户名不存在
            model.addAttribute("msg","用户名不存在!");
            return "login";
        }catch (IncorrectCredentialsException e){ //密码错误
            model.addAttribute("msg","密码错误!");
            return "login";
        }
    }
    @GetMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
}

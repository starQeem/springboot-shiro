package com.starQeem.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starQeem.dto.userDto;
import com.starQeem.pojo.user;
import com.starQeem.service.userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * @Date: 2023/5/18 10:55
 * @author: Qeem
 * 自定义的UserRealm
 */

public class UserRealm extends AuthorizingRealm {
    @Resource
    private userService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //用户名,密码  数据库中取
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","username","password").eq("username",userToken.getUsername());
        user user = userService.getBaseMapper().selectOne(queryWrapper);
        if (user == null){
            return null;  //抛出异常 UnknownAccountException
        }
        userDto userDto = new userDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        //密码认证(shiro做~)
        return new SimpleAuthenticationInfo(userDto,user.getPassword(),"");
    }
}

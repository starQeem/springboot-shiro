package com.starQeem.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Date: 2023/5/18 10:54
 * @author: Qeem
 */
@Configuration
public class ShiroConfig {
    //shiroFilterFatoryBean:3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
        * 添加shiro过滤器
        * anon:无需认证就可以访问
        * authc:必须认证了才能访问
        * user:必须拥有  记住我  功能才能用
        * parms:拥有对某个资源的权限才能访问
        * role:拥有某个角色权限才能访问
        * */
        LinkedHashMap<String,String> filterMap = new LinkedHashMap<>();
        //拦截
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        filterMap.put("/user/*","authc");
        //放行
//        filterMap.put("/user/*","anon");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        bean.setLoginUrl("/login");
        //登录成功后跳转的页面
//        bean.setSuccessUrl("/user/index");
        return bean;
    }
    //DafaultWebSecurityManger:2
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建realm对象,需要自定义类:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}

package com.finance.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.finance.common.AccountRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "sessionDAO")
    public MemorySessionDAO getMemorySessionDAO() {
        return new MemorySessionDAO();
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("SHRIOSESSIONID");
        return simpleCookie;
    }

    //配置shiro session 的一个管理器
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(getMemorySessionDAO());
        sessionManager.setSessionIdCookie(getSimpleCookie());
        return sessionManager;
    }

    //配置session的缓存管理器
    @Bean(name = "shiroCacheManager")
    public MemoryConstrainedCacheManager getMemoryConstrainedCacheManager() {
        return new MemoryConstrainedCacheManager();
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        //权限设置
        Map<String,String> map = new LinkedHashMap<>();

        map.put("/user/**","roles[user]");
        map.put("/admin/**","roles[admin]");
        //过滤
        map.put("/error/**","anon");
        map.put("/","anon");
        map.put("/toindex.html","anon");
        map.put("/tofindback.html","anon");
        map.put("/index.html","anon");
        map.put("/login/**","anon");
        map.put("/toregister.html","anon");
        map.put("/assert/**","anon");
        map.put("/bootstrap/**","anon");
        map.put("/images/**","anon");
        map.put("/lyear/**","anon");
        map.put("/js/**","anon");
        //对所有请求认证
        map.put("/**","authc");

        map.put("/logout*","logout");

        factoryBean.setFilterChainDefinitionMap(map);;
        factoryBean.setLoginUrl("/");

        return  factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm")AccountRealm accountRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }

    @Bean
    public AccountRealm accountRealm(){
        AccountRealm accountRealm1 = new AccountRealm();

        //修改凭证校验匹配器
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法MD5
//        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
//        credentialsMatcher.setHashIterations(1024);
//        accountRealm1.setCredentialsMatcher(credentialsMatcher);

        //开启缓存管理
        accountRealm1.setCacheManager(new EhCacheManager());
        accountRealm1.setCachingEnabled(true);//开启全局缓存
        //认证缓存和授权缓存
        accountRealm1.setAuthenticationCachingEnabled(true);
        accountRealm1.setAuthorizationCachingEnabled(true);

        return accountRealm1;
    }
    /**
     * 页面上使用shiro标签
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}

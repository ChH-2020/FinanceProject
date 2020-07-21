package com.finance.common;

import com.finance.pojo.admin.Admin;
import com.finance.pojo.admin.AdminPermissions;
import com.finance.pojo.user.User;
import com.finance.pojo.user.UserPermissions;
import com.finance.service.admin.permission.AdminPermissionService;
import com.finance.service.admin.permission.UserPermissionService;
import com.finance.service.login.LoginService;
import com.finance.service.user.userinfo.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserPermissionService userPermissionService;
    @Autowired
    private AdminPermissionService adminPermissionService;

    /**
     * 授权、权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行授权----------------！");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();

        String curUsername = (String) subject.getPrincipal();

        User user = (User) subject.getSession().getAttribute("loginUser");
        if(user!=null){
            info.addRole("user");
            List<UserPermissions> list = userPermissionService.selectUserPermissionsById(1);
            for(UserPermissions up: list){
                info.addStringPermission(up.getPermissions().getPermission());
            }
        }

        Admin admin = (Admin) subject.getSession().getAttribute("loginAdmin");
        if(admin!=null){
            info.addRole("admin");
            info.addRole("user");
            List<AdminPermissions> list = adminPermissionService.selectAdminPermissionsById(1);
            for(AdminPermissions ap: list){
                info.addStringPermission(ap.getPermissions().getPermission());
            }
        }
        return info;
    }

    /**
     * 认证、登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;


        User user = loginService.selectUserByName(token.getUsername());
        System.out.println(user);
        if(user != null){
            if(user.getStatus()==1){
                System.out.println("账号状态已经登录");
                throw new LockedAccountException();//账号已登陆
            }
            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            //状态修改不能在这里，万一登录失败，状态已被改变
            session.setAttribute("loginUser",user);//依据userId
            System.out.println("认证=====》登录\n用户："+user);
//            Md5Hash md5Hash = new Md5Hash(user.getPassword(),"",1024);
//            String password = md5Hash.toHex().substring(0,20);
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        }
        Admin admin = loginService.selectAdminByName(token.getUsername());
        if(admin != null){
//            if(admin.getStatus()==1){
//                throw new LockedAccountException();//账号已登陆
//            }
            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            session.setAttribute("loginAdmin",admin);
            System.out.println("认证=====》登录");
            return new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),getName());
        }
        return null;
    }
}

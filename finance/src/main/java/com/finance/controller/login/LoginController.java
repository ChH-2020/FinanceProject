package com.finance.controller.login;

import com.finance.common.LockHelper;
import com.finance.common.Result;
import com.finance.pojo.admin.Admin;
import com.finance.pojo.user.User;
import com.finance.service.login.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/toindex.html")
    public String toLoginPage(){
        return "login";
    }
    @RequestMapping("/index.html")
    public String toLoginPage2(){
        return "login";
    }

    @RequestMapping("/")
    public String toLoginPage1(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(@RequestParam("logout") String logout,HttpSession session){
        System.out.println(logout);
        Subject subject = SecurityUtils.getSubject();
        if(logout.endsWith("userLogout")){
            System.out.println("用户退出");
            if(((User)session.getAttribute("loginUser")) != null)
            {
                User user = ((User)session.getAttribute("loginUser"));
                loginService.updateUserOnlineStatus(user,"logout");
                if(((Admin)session.getAttribute("loginAdmin")) == null)
                {
//                    LockHelper.removeSession(user);//用这个会报错但是都可
                    LockHelper.userLogout();
                    subject.logout();
                }else
                    session.removeAttribute("loginUser");
                //会关闭user的session但是如果管理员和用户在同一浏览器即同一个session，那么管理员同样被下线
                //经验证，果然这样
                return "login";
            }
        }else {
            loginService.updateAdminOnlineStatus((Admin)session.getAttribute("loginAdmin"),"logout");
            if(((User)session.getAttribute("loginUser")) == null)
            {
                subject.logout();//如果同一浏览器下还有用户在登录，取消了管理员退出的清除session
            }else{
                session.removeAttribute("loginAdmin");
            }
        }
        System.out.println("返回登录页面");
        return "login";
    }

    @RequestMapping("/tofindback.html")
    public String findBackAccount(){
        return "findback";
    }

    @RequestMapping("/login/verifyIdentify")
    @ResponseBody
    public Result verifyIdentify(@RequestParam("username") String username,
                                 @RequestParam("phone_mail") String phone_mail,
                                 @RequestParam("password") String password){
        User user = loginService.selectUserByName(username);
        if(phone_mail.equals(user.getEmail())|| phone_mail.equals(user.getPhone()) || phone_mail.equals(user.getIdcard()))
        {
            user.setStatus(0);
            user.setPassword(password);
            System.out.println(user);
            int i = loginService.updateUserPassword(user);
            if(i == 1)
                return Result.success();
            else
                return Result.fail().add("msg","修改用户信息失败\n请稍后重试");
        }else
            return Result.fail();
    }

    @RequestMapping("/toregister.html")
    public String registerUser(){
        return "register";
    }

    @RequestMapping("/login/register")
    @ResponseBody
    public Result registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session){

        User user = new User();
        user.setUsername(username);
//        Md5Hash md5Hash = new Md5Hash(password,"",1024);
//        password = md5Hash.toHex().substring(0,20);
//        System.out.println(md5Hash.toHex()+"======"+password);
        user.setPassword(password);
        user.setReputation("良好");
        user.setStatus(0);//注册完直接跳转已登陆

        try {
            loginService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        System.out.println("-=======shiro======-");
        try{
            subject.login(token);
            LockHelper.addUser(session,(User)subject.getSession().getAttribute("loginUser"));
            loginService.updateUserOnlineStatus(user,"login");//状态修改在这里
            return Result.success().add("url","/user/index.html");
        }catch (Exception e){
            System.out.println("---密码错误---");
            return Result.fail();
        }
    }


    @RequestMapping("/login/loginVerifyUsername/{username}")
    @ResponseBody
    public Result loginVerifyUsername(@PathVariable("username") String username, HttpServletRequest request){
        int i = loginService.selectByName(username);
        System.out.println("-------检查用户名--------");
        System.out.println("查询到的用户个数"+i);
        if( i != 0){
            request.getSession().setAttribute("username",username);
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/login/verifyLogin")
    @ResponseBody
    public Result verifyLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session){//@RequestParam接值

        System.out.println("session的Id："+session.getId());
        System.out.println("session是不是新的："+session.isNew());

        String url = "/index.html";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = loginService.userLogin(user);
        if(user == null){
            url = "/admin" + url;
        }else {
            url = "/user"  + url;
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        System.out.println("shiro 的 sessionID : "+subject.getSession().getId());

        try{
            System.out.println("进入shiro=======》");
            subject.login(token);
            if(user!=null){
                loginService.updateUserOnlineStatus(user,"login");//内包括了修改用户状态
                LockHelper.addUser(session,user);
            }else {//手动修改用户状态
                Admin admin =(Admin)subject.getSession().getAttribute("loginAdmin");
                loginService.updateAdminOnlineStatus(admin,"login");
                session.setAttribute("loginAdmin",admin);
            }
            System.out.println("登录成功======！=");
            return Result.success().add("url",url);
        }catch (LockedAccountException le){
            return Result.fail().add("msg","已在别的设备登录");
        }catch (Exception e){
            System.out.println("---密码错误---");
            return Result.fail();
        }
    }

}

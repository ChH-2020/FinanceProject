package com.finance.controller.admin.common;

import com.finance.pojo.admin.Admin;
import com.finance.pojo.user.User;
import com.finance.service.user.userinfo.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping("/admin/index.html")
    public String adminMain(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                            Model model, HttpSession session){
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userInfoService.selectAllUser();

        //PageInfo封装分页信息
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("userPageInfo", pageInfo);
        model.addAttribute("userList", userList);
        model.addAttribute("activeUrl", "indexActive");

//        //先这样测试这
//        Admin admin = new Admin();
//        admin.setId(1);
//        admin.setUsername("ChH");
//        session.setAttribute("loginAdmin", admin);


        System.out.println("这里是admin主界面");
        return "admin/main";
    }



}

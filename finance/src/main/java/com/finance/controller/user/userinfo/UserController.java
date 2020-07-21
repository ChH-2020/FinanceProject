package com.finance.controller.user.userinfo;

import com.finance.common.LockHelper;
import com.finance.common.Result;
import com.finance.mapper.others.BankcardMapper;
import com.finance.pojo.admin.Admin;
import com.finance.pojo.others.Bankcard;
import com.finance.pojo.others.News;
import com.finance.pojo.user.User;
import com.finance.service.user.userinfo.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping("/index.html")
    public String userMain(Model model) {
        List<News> newsList = userInfoService.selectAllNews();

        model.addAttribute("newsList", newsList);
        model.addAttribute("activeUrl", "indexActive");

        System.out.println("用户主界面");
        return "user/main";
    }




    @RequestMapping("/addUser")
    @ResponseBody   //加入@ResponseBody后返回String不再返回视图解析
    public Result addUser(User user) {
        System.out.println(user);
        user.setStatus(0);
        user.setReputation("良好");
        int result = userInfoService.addUser(user);
        System.out.println(result);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/getUserById/{id}")
    @ResponseBody
    public Result getUserInfoById(@PathVariable("id") Integer id) {
        User user = userInfoService.selectUserById(id);
        return Result.success().add("user", user);
    }

    @RequestMapping("/updateUserProfile/{id}")
    @ResponseBody
    public Result updateUserProfile(@PathVariable("id") Integer id, User user) {
        System.out.println("---提交更新的用户信息---");
        System.out.println(user);
        int result = userInfoService.updateUser(user);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }


    //被强制下线
    @RequestMapping("/updateUserStatus/{id}")
    @ResponseBody
    public Result updateUserStatus(@PathVariable("id") Integer id,HttpSession session){
        //因为这个session是admin的不包含loginUser的Attribute
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        int i = userInfoService.updateUser(user);
        if(((Admin)session.getAttribute("loginAdmin")) == null)
            LockHelper.removeSession((User)session.getAttribute("loginUser"));
        else
            session.setAttribute("loginUser",null);
        if(i==1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }


    @RequestMapping("/deleteUserById/{id}")
    @ResponseBody
    public Result deleteUserById(@PathVariable("id") Integer id) {
        System.out.println("delete User ID:\t" + id);
        int i = userInfoService.deleteUserById(id);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/getBankCardById/{id}")
    @ResponseBody
    public Result getBankcardInfoById(@PathVariable("id") Integer id){
        Bankcard bankcard = userInfoService.selectBankcardById(id);
        System.out.println(bankcard);
        System.out.println("------得到银行卡信息回显------");
        return Result.success().add("bankcard",bankcard);
    }


    @RequestMapping("/updateBankCard/{id}")
    @ResponseBody
    public Result updateBankcard(@PathVariable("id") Integer id,Bankcard bankcard){
        System.out.println("------提交更新的银行卡信息------");
        System.out.println(bankcard);
        int result = userInfoService.updateBankcard(bankcard);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/deleteBankCard/{id}")
    @ResponseBody
    public Result deleteBankcardById(@PathVariable("id") Integer id){
        System.out.println("-----删除银行卡-----"+ id);
        int i = userInfoService.deleteBankcardById(id);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }
    @GetMapping("/historyinfo/unread")
    @ResponseBody
    public Result getUnreadMsg(@SessionAttribute("loginUser") User user) {
        int count = userInfoService.getUnReadInfoCountByUserId(user.getId());
        return Result.success().add("msgNum", count);
    }
}

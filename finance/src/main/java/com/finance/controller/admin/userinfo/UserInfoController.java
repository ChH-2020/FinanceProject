package com.finance.controller.admin.userinfo;

import com.finance.pojo.others.Bankcard;
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
@RequestMapping("/admin/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    
    @RequestMapping("/toUserInfo.html")
    public String selectAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                            Model model, HttpSession session) {
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userInfoService.selectAllUser();
//        System.out.println("用户信息列表界面"+userList);

        //PageInfo封装分页信息
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("userPageInfo", pageInfo);
        model.addAttribute("userList", userList);
        model.addAttribute("activeUrl1", "userInfoActive");
        model.addAttribute("activeUrl2", "userInfoActive");
        System.out.println("这里是admin用户列表界面");
        return "admin/userinfo/userinfo";
    }

    @RequestMapping("/toBankCard.html")
    public String bankcardInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                            Model model, HttpSession session) {
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);

        List<Bankcard> bankcardList = userInfoService.selectAllBankcard();

        //PageInfo封装分页信息
        PageInfo<Bankcard> pageInfo = new PageInfo<Bankcard>(bankcardList);
        model.addAttribute("bankcardPageInfo", pageInfo);
        model.addAttribute("bankcardList", bankcardList);
        model.addAttribute("activeUrl1", "userInfoActive");
        model.addAttribute("activeUrl2", "bankcardActive");
        System.out.println("这里是admin银行卡界面");
        return "admin/userinfo/bankcard";
    }

    @RequestMapping("/toReputation.html")
    public String selectAllReputation(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                            Model model, HttpSession session) {
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userInfoService.selectAllUser();

        //PageInfo封装分页信息
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("userPageInfo", pageInfo);
        model.addAttribute("userList", userList);
        model.addAttribute("activeUrl1", "userInfoActive");
        model.addAttribute("activeUrl2", "reputationActive");
        System.out.println("这里是admin用户列表界面");
        return "admin/userinfo/reputation";
    }

}

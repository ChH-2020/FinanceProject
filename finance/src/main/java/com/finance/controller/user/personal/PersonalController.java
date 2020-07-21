package com.finance.controller.user.personal;

import com.finance.mapper.user.UserMapper;
import com.finance.pojo.others.Bankcard;
import com.finance.pojo.others.Info;
import com.finance.pojo.others.Loan;
import com.finance.pojo.user.*;
import com.finance.service.user.personal.BankcardService;
import com.finance.service.user.personal.InfoService;
import com.finance.service.user.personal.MyFinanceService;
import com.finance.service.user.personal.MyLoanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/personal")
public class PersonalController {

    @Autowired
    private MyFinanceService myFinanceService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InfoService infoService;
    @Autowired
    private MyLoanService myLoanService;
    @Autowired
    public BankcardService bankcardService;


    @RequestMapping("/toMyFinance.html")
    public ModelAndView toMyFinance(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user/personal/myfinance");
        User user = (User) request.getSession().getAttribute("loginUser");
        int id = user.getId();
        List<UserPayMoney> userPayMoneyList = myFinanceService.selectUserPayMoneyById(id);
        List<UserChangeMoney> userChangeMoneyList = myFinanceService.selectUserChangeMoneyById(id);
        List<UserTermFinancial> userTermFinancialList = myFinanceService.selectUserTermFinancialById(id);
        List<UserFundProduct> userFundProductList = myFinanceService.selectUserFundProductById(id);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(userPayMoneyList);
        System.out.println(userChangeMoneyList);
        System.out.println(userTermFinancialList);
        System.out.println(userFundProductList);

        modelAndView.addObject("userChangeMoneyList",userChangeMoneyList);
        modelAndView.addObject("userPayMoneyList",userPayMoneyList);
        modelAndView.addObject("userTermFinancialList",userTermFinancialList);
        modelAndView.addObject("userFundProductList",userFundProductList);

        modelAndView.addObject("pageTopBarInfo","个人中心理财界面");
        modelAndView.addObject("activeUrl1","personalActive");
        modelAndView.addObject("activeUrl2","myFinanceActive");
        if(request.getSession().getAttribute("myFinanceActiveUrl") == null){
            modelAndView.addObject("myFinanceActiveUrl","changeMoneyActive");
        }
        System.out.println("--------------------------------fanhui-------------------------------------------");
        return modelAndView;
    }

    @RequestMapping("/toBankCard.html")
    public ModelAndView toBankCard(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user/personal/bankcard");
        User user1 = (User)request.getSession().getAttribute("loginUser");
        int id = user1.getId();
        List<Bankcard> bankcardList = bankcardService.selectAllBankcardByUserId(id);
        modelAndView.addObject("bankCardList",bankcardList);
        modelAndView.addObject("activeUrl1","personalActive");
        modelAndView.addObject("activeUrl2","bankCardActive");
        return modelAndView;
    }

    @RequestMapping("/toMyLoan.html")
    public String selectPersonalLoan(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                     Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        User user = (User) loginUser;

        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<Loan> loansList= myLoanService.selectRecordByUserId(user.getId());
        //PageInfo封装分页信息
        PageInfo<Loan> pageInfo = new PageInfo<Loan>(loansList);
        model.addAttribute("myLoansPageInfo", pageInfo);
        model.addAttribute("loansList", loansList);
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "myLoanActive");
        System.out.println("这里是用户"+user.getUsername()+"的借贷页面----------------------");
        System.out.println(loansList);
        return "user/personal/myloan";
    }

    @RequestMapping ("/toSecurity.html")
    public String toSecurity(Model model){
        model.addAttribute("activeUrl1","personalActive");
        model.addAttribute("activeUrl2","securityActive");
        return "user/personal/security";
    }



    @RequestMapping("/toProfile.html")
    public String userProfile(Model model, HttpSession session){
        Object loginUser = session.getAttribute("loginUser");
        User user = (User) loginUser;
        user = userMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("user",user);
        session.setAttribute("loginUser",user);
//        System.out.println("------用户个人信息界面------");
        return "user/personal/profile";
    }

    @RequestMapping("/toMyInfo.html")
    public String userInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                           Model model,HttpSession session){
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        User user = (User)session.getAttribute("loginUser");

        List<Info> infoList = infoService.selectAllMyInfo(user.getId());
//        System.out.println("---用户消息界面---");
        //PageInfo封装分页信息
        PageInfo<Info> pageInfo = new PageInfo<Info>(infoList);
        model.addAttribute("infoPageInfo", pageInfo);
        model.addAttribute("infoList", infoList);
        return "user/personal/myinfo";
    }


}

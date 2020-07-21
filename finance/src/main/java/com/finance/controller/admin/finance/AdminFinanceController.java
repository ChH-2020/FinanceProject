package com.finance.controller.admin.finance;

import com.finance.pojo.admin.Admin;
import com.finance.pojo.others.Bank;
import com.finance.pojo.others.ChangeMoney;
import com.finance.pojo.others.FundProduct;
import com.finance.pojo.others.TermFinancial;
import com.finance.service.admin.finance.BankService;
import com.finance.service.admin.finance.ChangeMoneyService;
import com.finance.service.admin.finance.TermFinancialService;
import com.finance.service.admin.finance.FundProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/finance")
public class AdminFinanceController {
    @Autowired
    private ChangeMoneyService changeMoneyService;

    @Autowired
    private TermFinancialService termFinancialService;

    @Autowired
    private FundProductService fundProductService;

    @Autowired
    private BankService bankService;

    @RequestMapping("/toChangeMoney.html")
    public String selectAllChangeMoney(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                       Model model, HttpSession session){
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<ChangeMoney> financeList = changeMoneyService.selectAllChangeMoney();

        //PageInfo封装分页信息
        PageInfo<ChangeMoney> pageInfo = new PageInfo<ChangeMoney>(financeList);
        model.addAttribute("finacnePageInfo", pageInfo);
        model.addAttribute("financeList", financeList);

        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "changemoneyActive");

        System.out.println("这里是finance列表界面");
        return "admin/finance/changemoney";
    }
    @RequestMapping("/toFundProduct.html")
    public ModelAndView toTermFinancial(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        ModelAndView modelAndView = new ModelAndView("admin/finance/fundproduct");
        PageHelper.startPage(pageNum,pageSize);
        List<FundProduct> fundProductList = fundProductService.selectALLFundProduct();
        PageInfo<FundProduct> fundProductPageInfo = new PageInfo<>(fundProductList);
        modelAndView.addObject("financeList",fundProductList);
        modelAndView.addObject("finacnePageInfo",fundProductPageInfo);//这里html里面就写错了，与其保持一致
        modelAndView.addObject("activeUrl1","financeActive");
        modelAndView.addObject("activeUrl2","fundproductActive");
        return modelAndView;
    }

    @RequestMapping("toTermFinancial.html")
    public String termFinancialPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                    Model model, HttpSession session){
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<TermFinancial> financeList = termFinancialService.selectAllTermFinancial();
        //PageInfo封装分页信息
        PageInfo<TermFinancial> pageInfo = new PageInfo<TermFinancial>(financeList);
        model.addAttribute("finacnePageInfo", pageInfo);//这里html里面就写错了，与其保持一致
        model.addAttribute("financeList",financeList );
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "termfinancialActive");

        System.out.println("这里是admin期限理财列表界面");

        return "admin/finance/termfinancial";
    }


    @RequestMapping("/toBank.html")
    public String selectALLBank(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                Model model, HttpSession session) {
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<Bank> financeList = bankService.selectAllBank();

        //PageInfo封装分页信息
        PageInfo<Bank> pageInfo = new PageInfo<Bank>(financeList);
        model.addAttribute("finacnePageInfo", pageInfo);
        model.addAttribute("financeList", financeList);
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "bankActive");
        System.out.println("这里是finance用户列表界面");
        return "admin/finance/bank";
    }
}

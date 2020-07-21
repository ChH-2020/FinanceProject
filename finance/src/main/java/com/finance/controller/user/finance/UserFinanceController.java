package com.finance.controller.user.finance;

import com.finance.pojo.others.*;
import com.finance.service.user.finance.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user/finance")
public class UserFinanceController {
    @Autowired
    private TermFinancialService termFinancialService;

    @Autowired
    private PayMoneyService payMoneyService;

    @Autowired
    private ChangeMoneyService changeMoneyService;

    @Autowired
    private BankService bankService;

    @Autowired
    FundProductService fundProductService;

    @RequestMapping("/toChangeMoney.html")
    public String selectAllRecords(Model model){
        List<ChangeMoney> changeMoneyList = changeMoneyService.selectAllRecords();
        model.addAttribute("changeMoneyList",changeMoneyList);
        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","changeMoneyActive");

        return "user/finance/changemoney";
    }

    @RequestMapping("/toTermFinancial.html")
    public String toTermFinancial(Model model){
//        System.out.println("-------期限理财界面----------");
        List<TermFinancial> termFinancialList = termFinancialService.selectAllTermFinancial();
        model.addAttribute("termFinancialList",termFinancialList);
        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","termFinancialActive");
        return "user/finance/termfinancial";
    }

    @RequestMapping("/toPayMoney.html")
    public String toPayMoney(Model model){
//        System.out.println("-------工资理财界面----------");
        List<PayMoney> payMoneyList = payMoneyService.selectAllPayMoney();
        model.addAttribute("payMoneyList",payMoneyList);
        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","payMoneyActive");
        return "user/finance/paymoney";
    }

    @RequestMapping("/toFundProduct.html")
    public ModelAndView toFundProduct(){
        ModelAndView modelAndView = new ModelAndView("user/finance/fundproduct");
        List<FundProduct> fundProductList = fundProductService.selectAllUserFundProduct();
        modelAndView.addObject("fundProductList",fundProductList);
        modelAndView.addObject("activeUrl1","financeActive");
        modelAndView.addObject("activeUrl2","fundProductActive");
        return modelAndView;
    }

    @RequestMapping("/toBank.html")
    public String toBank(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                         Model model){
//        System.out.println("-------银行推荐界面----------");
        List<Bank> bankList = bankService.selectAllBank();
        model.addAttribute("bankList",bankList);
        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","bankActive");
        return "user/finance/bank";
    }


}

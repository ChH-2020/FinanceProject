package com.finance.controller.user.finance;

import com.finance.common.Result;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.TermFinancial;
import com.finance.pojo.user.UserChangeMoney;
import com.finance.pojo.user.UserTermFinancial;
import com.finance.service.user.finance.TermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserTermFinancialController {

    @Autowired
    private TermFinancialService termFinancialService;

    @RequestMapping("/buyTermFinancial")
    @ResponseBody
    public Result buyTermFinancial(@RequestParam("termFinancialId") Integer termFinancialId,
                                   @RequestParam("userId") Integer userId){


        UserTermFinancial userTermFinancial = new UserTermFinancial();
        TermFinancial termFinancial = termFinancialService.selectById(termFinancialId);
        //填写user_term_financial表单数据
        userTermFinancial.setUserId(userId);
        userTermFinancial.setTermId(termFinancialId);
        userTermFinancial.setStartTime(new Date());
        userTermFinancial.setAverYield(termFinancial.getAnnualincome());
        BigDecimal money = termFinancial.getLeastmoney();
        BigDecimal rate = termFinancial.getAnnualincome();
        BigDecimal profit = money.multiply(rate);
        userTermFinancial.setProfit(profit);
        userTermFinancial.setStatus(1);
        //填写流水
        FlowOfFunds flowOfFunds = new FlowOfFunds();
        flowOfFunds.setUserid(userId);
        flowOfFunds.setCreatetime(new Date());
        flowOfFunds.setFlowmoney(termFinancial.getLeastmoney());
        flowOfFunds.setSource(termFinancial.getName());
        flowOfFunds.setType(1);
        flowOfFunds.setFunddesc("无");

        int i = termFinancialService.insertUserTermFinancial(userTermFinancial);
        int j = termFinancialService.insertFlowOfFunds(flowOfFunds);

        System.out.println("----限期投资----");

        if(i==1&&j==1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

}

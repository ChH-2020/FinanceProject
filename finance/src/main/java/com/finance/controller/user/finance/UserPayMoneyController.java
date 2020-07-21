package com.finance.controller.user.finance;

import com.finance.common.LockHelper;
import com.finance.common.Result;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.PayMoney;
import com.finance.pojo.user.UserPayMoney;
import com.finance.service.user.finance.PayMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserPayMoneyController {
    @Autowired
    private PayMoneyService payMoneyService;

    @RequestMapping("/buyPayMoney")
    @ResponseBody
    public Result buyPayMoney(@RequestParam("payMoneyId") Integer payMoneyId,
                              @RequestParam("userId") Integer userId){

        UserPayMoney userPayMoney = new UserPayMoney();
        PayMoney payMoney = payMoneyService.selectById(payMoneyId);
        //填写user_pay_money表单
        userPayMoney.setUserid(userId);
        userPayMoney.setPayid(payMoneyId);
        userPayMoney.setStarttime(new Date());
        userPayMoney.setStatus(1);
        double averYield = 0.03123000;//数据库中无收益率属性，但值都是这个
        userPayMoney.setAveryield(new BigDecimal(averYield));//平均收益率
        //国债》期货 自动转入》非
        double profit = LockHelper.StringListToInt(payMoney.getInvesterm());
        profit = profit * averYield;
        BigDecimal realprofit = new BigDecimal(profit);
        realprofit = realprofit.multiply(payMoney.getMonthmoney());
        userPayMoney.setProfit(realprofit);//收益 = 时间* 利率*月薪

        //填写流水
        FlowOfFunds flowOfFunds = new FlowOfFunds();
        flowOfFunds.setUserid(userId);
        flowOfFunds.setCreatetime(new Date());
        flowOfFunds.setFlowmoney(payMoney.getMonthmoney());
        flowOfFunds.setSource("工资理财");
        flowOfFunds.setType(1);
        if (payMoney.getType() == 1) {
            flowOfFunds.setFunddesc("国债");
        } else {
            flowOfFunds.setFunddesc("期货");
        }
        int i = payMoneyService.insertUserPayMoney(userPayMoney);
        int j = payMoneyService.insertFlowOfFunds(flowOfFunds);
        System.out.println("----工资买入----");

        if(i==1&&j==1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}

package com.finance.controller.user.finance;

import com.finance.common.Result;
import com.finance.pojo.others.ChangeMoney;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.user.UserChangeMoney;
import com.finance.service.user.finance.ChangeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class UserChangeMoneyController {
    @Autowired
    private ChangeMoneyService changeMoneyService;

    @RequestMapping("/user/buyChangeMoney")
    @ResponseBody
    public Result insertRecords(@RequestParam("userId") Integer userid, @RequestParam("changeMoneyId") Integer changeMoneyId){

        UserChangeMoney userChangeMoney = new UserChangeMoney();
        FlowOfFunds flowOfFunds = new FlowOfFunds();

        ChangeMoney changeMoney = changeMoneyService.selectById(changeMoneyId);
        //填写user_change_money记录
        userChangeMoney.setUserid(userid);
        userChangeMoney.setChangeid(changeMoneyId);
        userChangeMoney.setStarttime(new Date());
        userChangeMoney.setAveryield(changeMoney.getAnnualincome());
        BigDecimal param1 = changeMoney.getAnnualincome();
        BigDecimal param2 = changeMoney.getInvesmoney();
        BigDecimal param3 = param1.multiply(param2);
        //收益=投资*收益率
        userChangeMoney.setProfit(param3);
        userChangeMoney.setStatus(1);
        //填写资金流水记录
        flowOfFunds.setUserid(userid);
        flowOfFunds.setCreatetime(new Date());
        flowOfFunds.setFlowmoney(changeMoney.getInvesmoney());
        flowOfFunds.setSource(changeMoney.getName());
        flowOfFunds.setType(1);
        flowOfFunds.setFunddesc("无");

        int result1 = changeMoneyService.insertUserChangeMoney(userChangeMoney);
        int result2 = changeMoneyService.insertFlowOfFunds(flowOfFunds);
        System.out.println("----零钱投资----");
        if(result1 == 1&&result2 ==1){
            return Result.success();
        }else{
            return Result.fail();
        }
    }
}

package com.finance.controller.user.finance;

import com.finance.common.LockHelper;
import com.finance.common.Result;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.FundProduct;
import com.finance.pojo.user.UserFundProduct;
import com.finance.service.user.finance.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserFundProductController {
    @Autowired
    FundProductService fundProductService;

    @RequestMapping("/buyFundProduct")
    @ResponseBody
    public Result buyFundProduct(@RequestParam ("fundProductId")Integer fundProductId, @RequestParam("userId") Integer userId){
        UserFundProduct userFundProduct = new UserFundProduct();
        FlowOfFunds flowOfFunds = new FlowOfFunds();
        FundProduct fundProduct = fundProductService.selectFundProductById(fundProductId);
        //计算收益
        BigDecimal leastmoney = fundProduct.getLeastmoney();
        BigDecimal profit = leastmoney;
        BigDecimal averyield = fundProduct.getAnnualgrowth();
        String investerm = fundProduct.getInvesterm();
        double term = LockHelper.StringListToInt(investerm);
        profit = profit.multiply(new BigDecimal(term));
        if(investerm.endsWith("天") || investerm.endsWith("日")){
            profit = profit.multiply(fundProduct.getDailygrowth());
        }else if (investerm.endsWith("月")){
            profit = profit.multiply(fundProduct.getMonthlygrowth());
        }else {
            profit = profit.multiply(fundProduct.getMonthlygrowth());
        }

        //填写user_fund_product记录
        userFundProduct.setUserid(userId);
        userFundProduct.setFundid(fundProductId);
        userFundProduct.setStarttime(new Date());
        userFundProduct.setAveryield(averyield);//收益率=投资期限对应的增长率
        userFundProduct.setProfit(profit);//收益 = 收益率*投资固定期限*基金
        userFundProduct.setStatus(1);
        //填写流水记录
        flowOfFunds.setUserid(userId);
        flowOfFunds.setCreatetime(new Date());
        flowOfFunds.setFlowmoney(fundProduct.getLeastmoney());
        flowOfFunds.setSource(fundProduct.getFunddesc());
        flowOfFunds.setType(1);
        flowOfFunds.setFunddesc("无");

        int i = fundProductService.insertUserFundProduct(userFundProduct);
        int j = fundProductService.insertFlowOfFunds(flowOfFunds);
        System.out.println("----基金投资----");

        if(i == 1 && j == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}

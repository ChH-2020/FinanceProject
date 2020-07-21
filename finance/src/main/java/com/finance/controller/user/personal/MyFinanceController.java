package com.finance.controller.user.personal;

import com.finance.common.Result;
import com.finance.mapper.user.UserChangeMoneyMapper;
import com.finance.mapper.user.UserFundProductMapper;
import com.finance.mapper.user.UserPayMoneyMapper;
import com.finance.mapper.user.UserTermFinancialMapper;
import com.finance.pojo.user.*;
import com.finance.service.user.personal.MyFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class MyFinanceController {

    @Autowired
    public MyFinanceService myFinanceService;

    @Resource
    UserPayMoneyMapper userPayMoneyMapper;
    @Resource
    UserTermFinancialMapper userTermFinancialMapper;
    @Resource
    UserFundProductMapper userFundProductMapper;
    @Resource
    UserChangeMoneyMapper userChangeMoneyMapper;

    @PutMapping("/user/revokeUserChangeMoney")
    @ResponseBody
    public Result revokeUserChangeMoney(@PathParam("userChangeMoneyId") int userChangeMoneyId, UserChangeMoney userChangeMoney){
        userChangeMoney.setId(userChangeMoneyId);
//        System.out.println("----------==========userchangemoney====------------------");
        userChangeMoney.setStatus(3);
        int i = myFinanceService.updateUserChangeMoneyById(userChangeMoney);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/user/revokeUserPayMoney")
    @ResponseBody
    public Result revokeUserPayMoney(@PathParam("userPayMoneyId") int userPayMoneyId, UserPayMoney userPayMoney){
        userPayMoney.setId(userPayMoneyId);
        userPayMoney.setStatus(3);
        int i = myFinanceService.updateUserPayMoneyById(userPayMoney);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/user/revokeUserTermFinancial")
    @ResponseBody
    public Result revokeUserTermFinancial(@PathParam("userTermFinancialId") int userTermFinancialId, UserTermFinancial userTermFinancial){
        userTermFinancial.setId(userTermFinancialId);
        userTermFinancial.setStatus(3);
        int i = myFinanceService.updateUserTermFinancialById(userTermFinancial);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/user/revokeUserFundProduct")
    @ResponseBody
    public Result revokeUserFundProduct(@PathParam("userFundProductId") int userFundProductId, UserFundProduct userFundProduct){
        userFundProduct.setId(userFundProductId);
        userFundProduct.setStatus(3);
        int i = myFinanceService.updateUserFundProductById(userFundProduct);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

}





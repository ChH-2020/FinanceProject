package com.finance.controller.user.personal;

import com.finance.common.Result;
import com.finance.pojo.others.Loan;
import com.finance.pojo.user.User;
import com.finance.service.user.personal.MyLoanService;
import com.finance.service.user.personal.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoanAndSecurityController {
    @Autowired
    private MyLoanService myLoanService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping("/user/repayment/{id}")
    @ResponseBody
    public Result updateMyLoanRecords(@PathVariable("id") Integer id){
        Loan loan = new Loan();
        loan.setId(id);
        loan.setLoanstatus(2);
        int i = myLoanService.updateMyLoan(loan);
        if(i == 1){
            System.out.println("还款成功----------------");
            return Result.success();
        }else{
            System.out.println("还款失败----------------");
            return Result.fail();
        }

    }

    @RequestMapping("/user/updatePwd")
    @ResponseBody
    public Result updatePwd(@RequestParam("id") Integer userId,
                            @RequestParam("oldpwd") String oldpwd,
                            @RequestParam("newpwd") String newpwd){

        User user = new User();
        user.setId(userId);
        user.setPassword(newpwd);

        int i = securityService.updateUser(user);
        if( i == 1){
            System.out.println("密码修改成功");
            return Result.success();
        }else{
            System.out.println("密码修改失败");
            return Result.fail();
        }
    }


}

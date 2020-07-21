package com.finance.controller.admin.finance;

import com.finance.common.Result;
import com.finance.pojo.admin.Admin;
import com.finance.pojo.others.Bank;
import com.finance.pojo.user.User;
import com.finance.service.admin.finance.BankService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class BankController {
    @Autowired
    private BankService bankService;


    @RequestMapping("/admin/addBank" )
    @ResponseBody
    public Result insertBank(Bank bank){
        int i = bankService.insertBank(bank);
        if(i==1){
            return Result.success();
        }else {
            return Result.fail();
        }

    }

    @RequestMapping("/admin/getBankInfoById/{id}")
    @ResponseBody
    public Result getBankById(@PathVariable("id") Integer id){
        Bank bank = bankService.selectById((id));
        return Result.success().add("bank",bank);
    }

    @RequestMapping("/admin/updateBank/{id}")
    @ResponseBody
    public Result selectById(@PathVariable("id") Integer id ,Bank bank){
        int i = bankService.updateBank(bank);
        if(i==1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @RequestMapping("/admin/deleteBankById/{id}")
    @ResponseBody
    public Result deleteBank(@PathVariable("id") Integer id){
        int i = bankService.deleteBank(id);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }


}

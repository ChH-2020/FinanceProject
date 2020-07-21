package com.finance.controller.admin.finance;

import com.finance.common.Result;
import com.finance.pojo.others.TermFinancial;
import com.finance.service.admin.finance.TermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class TermFinancialController {
    @Autowired
    private TermFinancialService termFinancialService;

    //回显
    @RequestMapping("/getTermFinancialInfoById/{id}")
    @ResponseBody
    public Result getTermFinancialInfoById(@PathVariable("id") Integer id){
        TermFinancial termFinancial = termFinancialService.selectById(id);
        if(termFinancial!=null){
            return Result.success().add("termFinancial",termFinancial);
        }else {
            return Result.fail();
        }
    }


    //新增
    @RequestMapping("/addTermFinancial")
    @ResponseBody
    public Result addTermFinancial(TermFinancial termFinancial){
        int i = termFinancialService.insertTermFinancial(termFinancial);
        if(i==1){
            return Result.success();
        }else {
        return Result.fail();
        }
    }

    //更新
    @RequestMapping("/updateTermFinancial/{id}")
    @ResponseBody
    public Result updateTermFinancial(@PathVariable("id") Integer id,TermFinancial termFinancial){
        int i = termFinancialService.updateTermFinancial(termFinancial);
        System.out.println(termFinancial);
        if(i==1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @RequestMapping("/deleteTermFinancialById/{id}")
    @ResponseBody
    public Result deleteTermFinancialById(@PathVariable("id") Integer id){
        int i = termFinancialService.deleteTermFinancialById(id);
        if(i==1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}

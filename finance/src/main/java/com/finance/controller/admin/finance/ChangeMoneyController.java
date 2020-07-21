package com.finance.controller.admin.finance;

import com.finance.common.Result;
import com.finance.pojo.others.ChangeMoney;
import com.finance.service.admin.finance.ChangeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class ChangeMoneyController {
    @Autowired
    private ChangeMoneyService changeMoneyService;

    @RequestMapping("/getChangeMoneyInfoById/{id}")
    @ResponseBody
    public Result selectById(@PathVariable("id") Integer id){
        ChangeMoney changeMoney = changeMoneyService.selectById(id);
        if(changeMoney == null){
            return Result.fail();
        }else{
            return Result.success().add("changeMoney",changeMoney);
        }
    }


    @RequestMapping("/updateChangeMoney/{id}")
    @ResponseBody
    public Result updateChangeMoney(@PathVariable("id") Integer id,ChangeMoney changeMoney){
        System.out.println("更新");
        int result = changeMoneyService.updateChangeMoney(changeMoney);
        if(result==1){
            System.out.println("成功");
            return Result.success();
        } else{
            System.out.println("失败");
            return Result.fail();
        }
    }

    @RequestMapping("/deleteChangeMoneyById/{id}")
    @ResponseBody
    public Result deleteChangeMoney(@PathVariable("id") Integer id){
        System.out.println("delete changeMoney ID:\t" + id);
        int i = changeMoneyService.deleteChangeMoney(id);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @RequestMapping("/addChangeMoney")
    @ResponseBody
    public Result insertChangeMoney(ChangeMoney changeMoney){
        System.out.println(changeMoney);
        int result = changeMoneyService.insertChangeMoney(changeMoney);
        System.out.println(result);
        if(result == 1){
            return Result.success();
        }else{
            return Result.fail();
        }
    }
}

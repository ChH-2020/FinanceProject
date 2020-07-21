package com.finance.controller.admin.finance;

import com.finance.common.Result;
import com.finance.pojo.others.PayMoney;
import com.finance.service.admin.finance.PayMoneyService;
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
@RequestMapping("/admin")
public class PayMoneyController {
    @Autowired
    private PayMoneyService payMoneyService;


    @RequestMapping("/finance/toPayMoney.html")
    public String toPayMoney(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                             Model model, HttpSession session){
        List<PayMoney> payMoneyList = payMoneyService.selectAllPayMoney();


        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","paymoneyActive");

        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);

        //PageInfo封装分页信息
        PageInfo<PayMoney> pageInfo = new PageInfo<PayMoney>(payMoneyList);
        model.addAttribute("finacnePageInfo",pageInfo);
        model.addAttribute("financeList",payMoneyList);
        return "admin/finance/paymoney";
    }



    @RequestMapping("/getPayMoneyInfoById/{id}")
    @ResponseBody
    public Result getPayMoneyInfoById(@PathVariable("id") Integer id){
        PayMoney payMoney=payMoneyService.selectById(id);
        if(payMoney!=null){
            return Result.success().add("payMoney",payMoney);
        }else{
            return Result.fail();
        }
    }
    @RequestMapping("/addPayMoney")
    @ResponseBody
    public Result addPayMoney(PayMoney payMoney){
        int i=payMoneyService.insertPayMoney(payMoney);
        if(i==1){
            return Result.success();
        }else{
            return Result.fail();
        }
    }
    @RequestMapping("/updatePayMoney/{id}")
    @ResponseBody
    public Result updatePayMoney(@PathVariable("id") Integer id, PayMoney payMoney){
        int i=payMoneyService.updatePayMoney(payMoney);
        System.out.println(payMoney);
        if(i==1){
            return Result.success();
        }else{
            return Result.fail();
        }
    }

    @RequestMapping("/deletePayMoneyById/{id}")
    @ResponseBody
    public Result deletePayMoney(@PathVariable("id") Integer id, PayMoney payMoney){
        System.out.println("到达删除操作-----------");
        int i=payMoneyService.deletePayMoneyById(id);
        if(i==1){
            return Result.success();
        }else{
            return Result.fail();
        }
    }
}

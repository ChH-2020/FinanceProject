package com.finance.controller.user.personal;

import com.finance.common.Result;
import com.finance.pojo.others.Bankcard;
import com.finance.pojo.user.User;
import com.finance.service.user.personal.BankcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class BankcardController {
    @Autowired
    public BankcardService bankcardService;

    @RequestMapping("/user/addBankCard")
    @ResponseBody
    public Result insertBankcard(@PathParam("cardBank")String cardbank,
                                 @PathParam("type")Integer type,
                                 @PathParam("cardNum")String cardnum,
                                 HttpServletRequest request){
        System.out.println(666);
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        User user = (User)loginUser;
        Bankcard bankcard = new Bankcard();
        bankcard.setCardbank(cardbank);
        bankcard.setType(type);
        System.out.println(cardbank);
        bankcard.setCardnum(cardnum);
        bankcard.setUserid(user.getId());

        bankcard.setUserid(user.getId());
        bankcard.setCardnum(cardnum);
        int i = bankcardService.insertBankcard(bankcard);
        if (i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @RequestMapping("/user/getBankcardInfoById/{id}")
    @ResponseBody
    public Result getBankcardById(@PathVariable("id") Integer id){
        Bankcard bankcard = bankcardService.selectById((id));
        return Result.success().add("bankcard",bankcard);
    }

    @RequestMapping("/user/updateBankcard/{id}")
    @ResponseBody
    public Result selectById(@PathVariable("id") Integer id , Bankcard bankcard){
        int i = bankcardService.updateBankcard(bankcard);
        if(i==1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @RequestMapping("/user/deleteBankcardById/{id}")
    @ResponseBody
    public Result deleteBankcard(@PathVariable("id") Integer id){
        int i = bankcardService.deleteBankcard(id);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

}

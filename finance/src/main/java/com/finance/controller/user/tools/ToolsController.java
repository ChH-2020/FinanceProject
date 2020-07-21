package com.finance.controller.user.tools;

import com.finance.common.Result;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.Loan;
import com.finance.pojo.user.User;
import com.finance.service.user.tools.ApplyLoanService;
import com.finance.service.user.tools.RecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ToolsController {
    @Autowired
    private ApplyLoanService applyLoanService;

    @Autowired
    private RecordService recordService;

    @PostMapping("/user/applyLoan")
    @ResponseBody
    public Result insertRecord(@PathParam("amount") BigDecimal amount,
                               @PathParam("term") Integer term,
                               @PathParam("rate") BigDecimal rate,
                               HttpServletRequest request){
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        User user = (User)loginUser;

        Loan loan =new Loan();
        loan.setLoanid(user.getId());
        loan.setLoantime(new Date());
        loan.setRate(rate);
        loan.setAmount(amount);
        loan.setTerm(term);
        loan.setApplystatus(0);
        loan.setLoanstatus(0);

        int i = applyLoanService.insertLoan(loan);
        if( i == 1){
            System.out.println("网贷申请提交"+amount+"元");
            return Result.success();
        }else{
            System.out.println("网贷申请失败");
            return Result.fail();
        }
    }

    @GetMapping("/user/tools/toApplyLoan.html")
    public String toApplyLoan(Model model){
        System.out.println("------贷款界面------");
        model.addAttribute("activeUrl1", "toolsActive");
        model.addAttribute("activeUrl2", "applyLoanActive");
        return "user/tools/applyloan";
    }

    @GetMapping("/user/tools/toRecord.html")
    public String selectRecord(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                               Model model,HttpServletRequest request){
        System.out.println("------贷款记录界面------");

        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        User user = (User) loginUser;

        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<FlowOfFunds> flowOfFundsList = recordService.selectAllRecordsByUserId(user.getId());

        //PageInfo封装分页信息
        PageInfo<FlowOfFunds> pageInfo = new PageInfo<FlowOfFunds>(flowOfFundsList);
        model.addAttribute("flowOfFundsPageInfo", pageInfo);
        model.addAttribute("flowOfFundsList", flowOfFundsList);
        model.addAttribute("activeUrl1", "toolsActive");
        model.addAttribute("activeUrl2", "recordActive");
        System.out.println("这里是用户FlowOfFunds列表界面");
        return "user/tools/record";
    }
}

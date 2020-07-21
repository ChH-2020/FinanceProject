package com.finance.controller.admin.loan;

import com.finance.common.Result;
import com.finance.pojo.admin.Admin;
import com.finance.pojo.others.Info;
import com.finance.pojo.others.Loan;
import com.finance.service.admin.loan.LoanExamService;
import com.finance.service.admin.loan.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/loan")
public class LoanMethodsController {
    @Autowired
    private LoanExamService loanExamService;

    @Autowired
    private LoanInfoService loanInfoService;


    //审核通过通知
    @RequestMapping("/passApplyStatus/{id}")
    @ResponseBody
    public Result updateLoanExam(@PathVariable("id") Integer id,HttpSession session){
        Loan loan = new Loan();
        loan.setId(id);
        loan.setExamineid(((Admin)session.getAttribute("loginAdmin")).getId());
        loan.setApplystatus(2);
        System.out.println("更新");

        int result = loanExamService.updateLoanExam(loan);
        Loan loan2 = loanExamService.selectLoanExamById(id);
        if(result==1){
            Object loginAdmin = session.getAttribute("loginAdmin");
            Admin admin = (Admin) loginAdmin;
            //填写info信息
            Info info = new Info();
            System.out.println("adminId ==="+admin.getId());
            info.setSendid(admin.getId());
            info.setReceiveid(loan2.getLoanid());
            info.setTitle("网贷审核通过");
            info.setCreatetime(new Date());
            Loan loan1 = loanInfoService.selectLoanInfoById(id);
            String desc = "用户" + loan1.getUser().getUsername() + "申请的" + loan1.getAmount()
                    + "元网贷申请审核通过！审核人为："+ admin.getUsername();
            info.setInfodesc(desc);
            info.setStatus(0);
            int i = loanInfoService.insertLoanInfo(info);
            return Result.success();
        }
        else{
            return Result.fail();
        }
    }

    @RequestMapping("/notPassApplyStatus/{id}")
    @ResponseBody
    public Result updateLoanExam2(@PathVariable("id") Integer id,HttpSession session){
        Loan loan = new Loan();
        loan.setId(id);
        loan.setExamineid(((Admin)session.getAttribute("loginAdmin")).getId());
        loan.setApplystatus(1);
        System.out.println("更新审核状态");

        int result = loanExamService.updateLoanExam(loan);
        Loan loan2 = loanExamService.selectLoanExamById(id);
        if(result==1){
            Object loginAdmin = session.getAttribute("loginAdmin");
            Admin admin = (Admin) loginAdmin;
            //填写info信息
            Info info = new Info();
            System.out.println("adminId ==="+admin.getId());
            info.setSendid(admin.getId());
            info.setReceiveid(loan2.getLoanid());
            info.setTitle("网贷审核未通过");
            info.setCreatetime(new Date());
            Loan loan1 = loanInfoService.selectLoanInfoById(id);
            String desc = "用户" + loan1.getUser().getUsername() + "申请的" + loan1.getAmount()
                    + "元网贷申请审核未通过！审核人为："+ admin.getUsername();
            info.setInfodesc(desc);
            info.setStatus(0);
            int i = loanInfoService.insertLoanInfo(info);
            return Result.success();
        }
        else{
            System.out.println("失败");
            return Result.fail();
        }
    }


    @RequestMapping("/remindPay/{id}")
    @ResponseBody
    public Result remindPay(@PathVariable("id") Integer id,HttpSession session){
        Object loginAdmin = session.getAttribute("loginAdmin");
        Admin admin = (Admin) loginAdmin;
        //填写info信息
        Info info = new Info();
        info.setSendid(admin.getId());
        info.setTitle("还款通知");
        info.setCreatetime(new Date());
        Loan loan = loanInfoService.selectLoanInfoById(id);
        info.setReceiveid(loan.getLoanid());
        String desc = "用户" + loan.getUser().getUsername() + "申请的" + loan.getAmount()
                + "元贷款该还款了！该提醒发送人为："+ admin.getUsername();
//        System.out.println(desc);
        info.setInfodesc(desc);
        info.setStatus(0);
        int i = loanInfoService.insertLoanInfo(info);
        if(i == 1) {
            return Result.success();
        }else{
            return Result.fail();
        }

    }
}

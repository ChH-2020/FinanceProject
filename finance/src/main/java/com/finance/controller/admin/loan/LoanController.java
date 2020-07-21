package com.finance.controller.admin.loan;

import com.finance.pojo.others.Loan;
import com.finance.service.admin.loan.LoanExamService;
import com.finance.service.admin.loan.LoanInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/loan")
public class LoanController {
    @Autowired
    private LoanExamService loanExamService;
    @Autowired
    private LoanInfoService loanInfoService;

    @RequestMapping("/toLoanexam.html")
    public String selectAllLoanExam(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                            Model model, HttpSession session) {
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<Loan> loanList = loanExamService.selectAllLoanExam();

        //PageInfo封装分页信息
        PageInfo<Loan> pageInfo = new PageInfo<Loan>(loanList);
        model.addAttribute("loanPageInfo", pageInfo);
        model.addAttribute("loanList", loanList);
        model.addAttribute("activeUrl1", "loanActive");
        model.addAttribute("activeUrl2", "loanexamActive");
        System.out.println("这里是loanexam列表界面");
        return "admin/loan/loanexam";
    }

    @RequestMapping("/toLoaninfo.html")
    public String selectAllLoanInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                            Model model, HttpSession session) {
        //引入pageHelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<Loan> loanList = loanInfoService.selectAllLoanInfo();

        //PageInfo封装分页信息
        PageInfo<Loan> pageInfo = new PageInfo<Loan>(loanList);
        model.addAttribute("loanPageInfo", pageInfo);
        model.addAttribute("loanList", loanList);
        model.addAttribute("activeUrl1", "loanActive");
        model.addAttribute("activeUrl2", "loaninfoActive");
        System.out.println("这里是loaninfo列表界面");
        return "admin/loan/loaninfo";
    }


}

package com.finance.service.admin.loan;

import com.finance.pojo.others.Info;
import com.finance.pojo.others.Loan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoanInfoService {
    public List<Loan> selectAllLoanInfo();
    public Loan selectLoanInfoById(Integer id);
    public int insertLoanInfo(Info info);

}

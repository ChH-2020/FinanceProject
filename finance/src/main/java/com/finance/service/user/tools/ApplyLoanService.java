package com.finance.service.user.tools;

import com.finance.pojo.others.Loan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplyLoanService {
    public int insertLoan(Loan loan);
}
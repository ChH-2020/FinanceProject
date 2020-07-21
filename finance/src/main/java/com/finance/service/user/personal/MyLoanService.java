package com.finance.service.user.personal;

import com.finance.pojo.others.Loan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyLoanService {
    public List<Loan> selectRecordByUserId(Integer id);
    public int updateMyLoan(Loan loan);
}

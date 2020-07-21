package com.finance.service.admin.loan;

import com.finance.pojo.others.Loan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoanExamService {
    public List<Loan> selectAllLoanExam();
    public int updateLoanExam(Loan loan);
    public  Loan selectLoanExamById(Integer id);
}

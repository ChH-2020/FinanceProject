package com.finance.service.impl.admin.loan;

import com.finance.mapper.others.LoanMapper;
import com.finance.pojo.others.Loan;
import com.finance.pojo.others.LoanExample;
import com.finance.service.admin.loan.LoanExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanExamServiceImpl implements LoanExamService {
    @Autowired
    private LoanMapper loanMapper;

    @Override
    public List<Loan> selectAllLoanExam() {
        LoanExample loanExample = new LoanExample();
        loanExample.setOrderByClause("loanTime desc");
        return loanMapper.selectByExample(loanExample);
    }

    @Override
    public int updateLoanExam(Loan loan) {
        return loanMapper.updateByPrimaryKeySelective(loan);
    }

    @Override
    public Loan selectLoanExamById(Integer id) {
        return loanMapper.selectByPrimaryKey(id);
    }

}

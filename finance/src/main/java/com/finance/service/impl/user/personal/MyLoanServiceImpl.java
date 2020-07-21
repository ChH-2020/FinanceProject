package com.finance.service.impl.user.personal;

import com.finance.mapper.others.LoanMapper;
import com.finance.pojo.others.Loan;
import com.finance.pojo.others.LoanExample;
import com.finance.service.user.personal.MyLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyLoanServiceImpl implements MyLoanService {
    @Autowired
    private LoanMapper loanMapper;

    @Override
    public List<Loan> selectRecordByUserId(Integer id) {
        LoanExample loanExample =new LoanExample();
        LoanExample.Criteria criteria = loanExample.createCriteria();
        criteria.andLoanidEqualTo(id);
        return loanMapper.selectByExample(loanExample);
    }

    @Override
    public int updateMyLoan(Loan loan) {
        LoanExample loanExample = new LoanExample();
        LoanExample.Criteria criteria = loanExample.createCriteria();
        criteria.andIdEqualTo(loan.getId());
        return loanMapper.updateByPrimaryKeySelective(loan);
    }
}

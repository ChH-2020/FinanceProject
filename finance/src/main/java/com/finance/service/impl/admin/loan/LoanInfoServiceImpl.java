package com.finance.service.impl.admin.loan;

import com.finance.mapper.others.InfoMapper;
import com.finance.mapper.others.LoanMapper;
import com.finance.pojo.others.Info;
import com.finance.pojo.others.Loan;
import com.finance.pojo.others.LoanExample;
import com.finance.service.admin.loan.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanInfoServiceImpl implements LoanInfoService {
    @Autowired
    private LoanMapper loanMapper;

    @Override
    public List<Loan> selectAllLoanInfo() {
        LoanExample loanExample = new LoanExample();
        LoanExample.Criteria criteria = loanExample.createCriteria();
        criteria.andApplystatusEqualTo(2);
        loanExample.setOrderByClause("loanTime desc");
        return loanMapper.selectByExample(loanExample);
    }

    @Override
    public Loan selectLoanInfoById(Integer id) {
        return loanMapper.selectByPrimaryKey(id);
    }

    @Autowired
    private InfoMapper infoMapper;

    @Override
    public int insertLoanInfo(Info info) {
        return infoMapper.insert(info);
    }
}

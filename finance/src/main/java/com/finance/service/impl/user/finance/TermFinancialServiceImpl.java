package com.finance.service.impl.user.finance;

import com.finance.mapper.others.FlowOfFundsMapper;
import com.finance.mapper.others.TermFinancialMapper;
import com.finance.mapper.user.UserTermFinancialMapper;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.TermFinancial;
import com.finance.pojo.user.UserTermFinancial;
import com.finance.service.user.finance.TermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermFinancialServiceImpl implements TermFinancialService {
    @Autowired
    private TermFinancialMapper termFinancialMapper;
    @Autowired
    private UserTermFinancialMapper userTermFinancialMapper;
    @Autowired
    private FlowOfFundsMapper flowOfFundsMapper;

    @Override
    public List<TermFinancial> selectAllTermFinancial() {
        return termFinancialMapper.selectByExample(null);
    }

    @Override
    public TermFinancial selectById(Integer id) {
        return termFinancialMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertUserTermFinancial(UserTermFinancial usertermFinancial) {
        return userTermFinancialMapper.insertSelective(usertermFinancial);
    }

    @Override
    public int insertFlowOfFunds(FlowOfFunds flowOfFunds) {
        return flowOfFundsMapper.insertSelective(flowOfFunds);
    }
}

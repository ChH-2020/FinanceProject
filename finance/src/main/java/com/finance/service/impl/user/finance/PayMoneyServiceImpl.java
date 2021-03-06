package com.finance.service.impl.user.finance;

import com.finance.mapper.others.FlowOfFundsMapper;
import com.finance.mapper.others.PayMoneyMapper;
import com.finance.mapper.user.UserPayMoneyMapper;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.PayMoney;
import com.finance.pojo.user.UserPayMoney;
import com.finance.service.user.finance.PayMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PayMoneyServiceImpl implements PayMoneyService {
    @Autowired
    private PayMoneyMapper payMoneyMapper;
    @Autowired
    private UserPayMoneyMapper userPayMoneyMapper;
    @Autowired
    private FlowOfFundsMapper flowOfFundsMapper;


    @Override
    public List<PayMoney> selectAllPayMoney() {
        return payMoneyMapper.selectByExample(null);
    }

    @Override
    public PayMoney selectById(Integer id) {
        return payMoneyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertFlowOfFunds(FlowOfFunds flowOfFunds) {
        return flowOfFundsMapper.insertSelective(flowOfFunds);
    }

    @Override
    public int insertUserPayMoney(UserPayMoney userPayMoney) {
        return userPayMoneyMapper.insertSelective(userPayMoney);
    }
}

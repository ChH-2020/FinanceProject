package com.finance.service.user.finance;

import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.PayMoney;
import com.finance.pojo.user.UserPayMoney;

import java.util.List;

public interface PayMoneyService {
    public List<PayMoney> selectAllPayMoney();
    public PayMoney selectById(Integer id);
    public int insertFlowOfFunds(FlowOfFunds flowOfFunds);
    public int insertUserPayMoney(UserPayMoney userPayMoney);
}

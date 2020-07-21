package com.finance.service.admin.finance;

import com.finance.pojo.others.PayMoney;

import java.util.List;

public interface PayMoneyService {
    public List<PayMoney> selectAllPayMoney();
    public PayMoney selectById(Integer id);
    public int insertPayMoney(PayMoney payMoney);
    public int updatePayMoney(PayMoney payMoney);
    public int deletePayMoneyById(Integer id);
}

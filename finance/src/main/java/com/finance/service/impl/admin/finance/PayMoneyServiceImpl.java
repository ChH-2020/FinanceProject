package com.finance.service.impl.admin.finance;

import com.finance.mapper.others.PayMoneyMapper;
import com.finance.pojo.others.PayMoney;
import com.finance.service.admin.finance.PayMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayMoneyServiceImpl implements PayMoneyService {
    @Autowired
    private PayMoneyMapper payMoneyMapper;

    @Override
    public List<PayMoney> selectAllPayMoney() {
        return payMoneyMapper.selectByExample(null);
    }

    @Override
    public PayMoney selectById(Integer id) {
        return payMoneyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertPayMoney(PayMoney payMoney) {
        System.out.println("----插入----");
        return payMoneyMapper.insertSelective(payMoney);
    }

    @Override
    public int updatePayMoney(PayMoney payMoney) {
        System.out.println("----更新----");
        return payMoneyMapper.updateByPrimaryKey(payMoney);
    }

    @Override
    public int deletePayMoneyById(Integer id) {
        System.out.println("----删除----");
        return payMoneyMapper.deleteByPrimaryKey(id);
    }
}

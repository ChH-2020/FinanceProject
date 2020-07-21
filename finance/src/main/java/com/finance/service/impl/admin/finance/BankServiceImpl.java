package com.finance.service.impl.admin.finance;

import com.finance.mapper.others.BankMapper;
import com.finance.pojo.others.Bank;
import com.finance.pojo.others.BankExample;
import com.finance.service.admin.finance.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankMapper bankMapper;

    @Override
    public List<Bank> selectAllBank(){
        System.out.println("查询所有银行信息");
        return bankMapper.selectByExample(null);
    }

    @Override
    public int updateBank(Bank bank){
        System.out.println("编辑银行信息");
        BankExample bankExample = new BankExample();
        BankExample.Criteria criteria = bankExample.createCriteria();
        criteria.andIdEqualTo(bank.getId());
        return bankMapper.updateByPrimaryKeySelective(bank);
    }

    @Override
    public int deleteBank(Integer id){
        System.out.println("删除银行信息"+id);
        return bankMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Bank selectById(Integer id){
        return bankMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertBank(Bank bank){
        System.out.println("增加银行信息");
        return bankMapper.insertSelective(bank);
    }


}

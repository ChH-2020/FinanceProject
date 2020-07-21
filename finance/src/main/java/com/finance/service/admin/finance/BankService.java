package com.finance.service.admin.finance;

import com.finance.pojo.others.Bank;
import com.finance.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankService {

    public List<Bank> selectAllBank();
    public int updateBank(Bank bank);
    public int deleteBank(Integer integer);
    public Bank selectById(Integer integer);
    public int insertBank(Bank bank);

}

package com.finance.service.user.personal;

import com.finance.pojo.others.Bankcard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankcardService {
    public List<Bankcard> selectAllBankcardByUserId(Integer userId);
    public int updateBankcard(Bankcard bankcard);
    public int deleteBankcard(Integer integer);
    public Bankcard selectById(Integer integer);
    public int insertBankcard(Bankcard bankcard);

}

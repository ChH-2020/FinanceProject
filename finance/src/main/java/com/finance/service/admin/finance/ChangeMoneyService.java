package com.finance.service.admin.finance;

import com.finance.pojo.others.ChangeMoney;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChangeMoneyService {
    public List<ChangeMoney> selectAllChangeMoney();
    public int updateChangeMoney(ChangeMoney changeMoney);
    public int deleteChangeMoney(Integer integer);
    public ChangeMoney selectById(Integer integer);
    public int insertChangeMoney(ChangeMoney changeMoney);
}

package com.finance.service.user.finance;

import com.finance.pojo.others.ChangeMoney;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.user.UserChangeMoney;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChangeMoneyService {
    public List<ChangeMoney> selectAllRecords();

    public int insertUserChangeMoney(UserChangeMoney userChangeMoney);
    public ChangeMoney selectById(Integer id);
    public int insertFlowOfFunds(FlowOfFunds flowOfFunds);
}

package com.finance.service.user.finance;

import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.TermFinancial;
import com.finance.pojo.user.UserTermFinancial;

import java.util.List;

public interface TermFinancialService {
    public List<TermFinancial> selectAllTermFinancial();
    public TermFinancial selectById(Integer id);
    public int insertUserTermFinancial(UserTermFinancial userTermFinancial);
    public int insertFlowOfFunds(FlowOfFunds flowOfFunds);

}

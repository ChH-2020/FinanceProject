package com.finance.service.admin.finance;

import com.finance.pojo.others.TermFinancial;

import java.util.List;

public interface TermFinancialService {
    public List<TermFinancial> selectAllTermFinancial();
    public TermFinancial selectById(Integer id);
    public int insertTermFinancial(TermFinancial termFinancial);
    public int updateTermFinancial(TermFinancial termFinancial);
    public int deleteTermFinancialById(Integer id);
}

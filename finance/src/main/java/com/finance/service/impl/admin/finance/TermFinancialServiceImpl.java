package com.finance.service.impl.admin.finance;

import com.finance.mapper.others.TermFinancialMapper;
import com.finance.pojo.others.TermFinancial;
import com.finance.service.admin.finance.TermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermFinancialServiceImpl implements TermFinancialService {
    @Autowired
    private TermFinancialMapper termFinancialMapper;

    @Override
    public List<TermFinancial> selectAllTermFinancial() {
        return termFinancialMapper.selectByExample(null);
    }

    @Override
    public TermFinancial selectById(Integer id) {
        return termFinancialMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertTermFinancial(TermFinancial termFinancial) {
        return termFinancialMapper.insert(termFinancial);
    }

    @Override
    public int updateTermFinancial(TermFinancial termFinancial) {
//        TermFinancialExample termFinancialExample = new TermFinancialExample();
//        TermFinancialExample.Criteria criteria = termFinancialExample.createCriteria();
//        criteria.andIdEqualTo(termFinancial.getId());
        return termFinancialMapper.updateByPrimaryKeySelective(termFinancial);
    }

    @Override
    public int deleteTermFinancialById(Integer id) {
        return termFinancialMapper.deleteByPrimaryKey(id);
    }
}

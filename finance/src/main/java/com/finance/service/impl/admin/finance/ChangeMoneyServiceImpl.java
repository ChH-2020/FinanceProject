package com.finance.service.impl.admin.finance;

import com.finance.mapper.others.ChangeMoneyMapper;
import com.finance.pojo.others.ChangeMoney;
import com.finance.pojo.others.ChangeMoneyExample;
import com.finance.service.admin.finance.ChangeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeMoneyServiceImpl implements ChangeMoneyService {
    @Autowired
    private ChangeMoneyMapper changeMoneyMapper;

    @Override
    public List<ChangeMoney> selectAllChangeMoney() {
        System.out.println("查询全部零钱理财信息");
        return changeMoneyMapper.selectByExample(null);
    }

    @Override
    public int updateChangeMoney(ChangeMoney changeMoney) {
        System.out.println("----------编辑信息----------");
        ChangeMoneyExample changeMoneyExample = new ChangeMoneyExample();
        ChangeMoneyExample.Criteria criteria = changeMoneyExample.createCriteria();
        criteria.andIdEqualTo(changeMoney.getId());
        return changeMoneyMapper.updateByPrimaryKeySelective(changeMoney);
    }

    @Override
    public int deleteChangeMoney(Integer id) {
        System.out.println("-------删除零钱理财---------"+id);
        return changeMoneyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ChangeMoney selectById(Integer id) {
        return changeMoneyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertChangeMoney(ChangeMoney changeMoney) {
        System.out.println("-------增加零钱理财-----------");
        return changeMoneyMapper.insertSelective(changeMoney);
    }
}

package com.finance.service.impl.user.personal;

import com.finance.mapper.others.BankcardMapper;
import com.finance.pojo.others.Bankcard;
import com.finance.pojo.others.BankcardExample;
import com.finance.service.user.personal.BankcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankcardServiceImpl implements BankcardService {
    @Autowired
    private BankcardMapper bankcardMapper;

    @Override
    public List<Bankcard> selectAllBankcardByUserId(Integer userId){
        System.out.println("查询所有银行卡信息");
        BankcardExample bankcardExample = new BankcardExample();
        BankcardExample.Criteria criteria = bankcardExample.createCriteria();
        criteria.andUseridEqualTo(userId);
        return bankcardMapper.selectByExample(bankcardExample);
    }

    @Override
    public int updateBankcard(Bankcard bankcard){
        System.out.println("编辑银行卡信息");
        BankcardExample bankcardExample = new BankcardExample();
        BankcardExample.Criteria criteria = bankcardExample.createCriteria();
        criteria.andIdEqualTo(bankcard.getId());
        return bankcardMapper.updateByPrimaryKeySelective(bankcard);
    }

    @Override
    public int deleteBankcard(Integer id){
        System.out.println("删除银行卡信息"+id);
        return bankcardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Bankcard selectById(Integer id){
        return bankcardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertBankcard(Bankcard bankcard){
        System.out.println("增加银行卡信息");
        return bankcardMapper.insertSelective(bankcard);
    }
}

package com.finance.service.impl.user.finance;

import com.finance.mapper.others.ChangeMoneyMapper;
import com.finance.mapper.others.FlowOfFundsMapper;
import com.finance.mapper.user.UserChangeMoneyMapper;
import com.finance.pojo.others.ChangeMoney;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.user.UserChangeMoney;
import com.finance.service.user.finance.ChangeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangMoneyServiceImpl implements ChangeMoneyService {
    @Autowired
    private ChangeMoneyMapper changeMoneyMapper;
    @Autowired
    private UserChangeMoneyMapper userChangeMoneyMapper;
    @Autowired
    private FlowOfFundsMapper flowOfFundsMapper;

    @Override
    public List<ChangeMoney> selectAllRecords() {
        return changeMoneyMapper.selectByExample(null);
    }

    @Override
    public int insertUserChangeMoney(UserChangeMoney userChangeMoney) {
        return userChangeMoneyMapper.insertSelective(userChangeMoney);
//        HttpSession session = request.getSession();
//        Object loginUser = session.getAttribute("loginUser");
//        User user = (User)loginUser;

//        UserChangeMoneyExample userChangeMoneyExample = new UserChangeMoneyExample();
//        UserChangeMoneyExample.Criteria criteria = userChangeMoneyExample.createCriteria();
//        criteria.andUseridEqualTo(user.getId());
//        return userChangeMoneyMapper.insertSelective();
    }

    @Override
    public ChangeMoney selectById(Integer id) {
        return changeMoneyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertFlowOfFunds(FlowOfFunds flowOfFunds) {
        return flowOfFundsMapper.insertSelective(flowOfFunds);
    }

//    @Override
//    public int insertUserChangeMoney(UserChangeMoney userChangeMoney) {
//
//    }

//    @Override
//    public List<UserChangeMoney> selectById(Integer id) {
//        System.out.println("查询个人全部零钱理财");
//        UserChangeMoneyExample userChangeMoneyExample = new UserChangeMoneyExample();
//        UserChangeMoneyExample.Criteria criteria = userChangeMoneyExample.createCriteria();
//        criteria.andUseridEqualTo(id);
//        return userChangeMoneyMapper.selectByExample(userChangeMoneyExample);
//    }
}

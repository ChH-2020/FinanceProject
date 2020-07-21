package com.finance.service.impl.user.finance;

import com.finance.mapper.others.FlowOfFundsMapper;
import com.finance.mapper.others.FundProductMapper;
import com.finance.mapper.user.UserFundProductMapper;
import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.FundProduct;
import com.finance.pojo.user.UserFundProduct;
import com.finance.service.user.finance.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundProductServiceImpl implements FundProductService {
    @Autowired
    private FundProductMapper fundProductMapper;
    @Autowired
    private UserFundProductMapper userFundProductMapper;
    @Autowired
    private FlowOfFundsMapper flowOfFundsMapper;

    @Override
    public List<FundProduct> selectAllUserFundProduct() {
        return fundProductMapper.selectByExample(null);
    }

    @Override
    public FundProduct selectFundProductById(Integer id) {
        return fundProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertFlowOfFunds(FlowOfFunds flowOfFunds) {
        return flowOfFundsMapper.insertSelective(flowOfFunds);
    }

    @Override
    public int insertUserFundProduct(UserFundProduct userFundProduct) {
        return userFundProductMapper.insertSelective(userFundProduct);
    }
}

package com.finance.service.user.finance;

import com.finance.pojo.others.FlowOfFunds;
import com.finance.pojo.others.FundProduct;
import com.finance.pojo.user.UserFundProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface FundProductService {
    public List<FundProduct> selectAllUserFundProduct();
    public int insertUserFundProduct(UserFundProduct userFundProduct);
    public FundProduct selectFundProductById(Integer id);
    public int insertFlowOfFunds(FlowOfFunds flowOfFunds);

}

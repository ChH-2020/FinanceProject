package com.finance.service.impl.admin.finance;

import com.finance.mapper.others.FundProductMapper;
import com.finance.pojo.others.FundProduct;
import com.finance.service.admin.finance.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundProductServiceImpl implements FundProductService {
    @Autowired
    private FundProductMapper fundProductMapper;

    @Override
    public List<FundProduct> selectALLFundProduct() {
        return fundProductMapper.selectByExample(null);
    }

    @Override
    public int insertFundProduct(FundProduct fundProduct) {
        return fundProductMapper.insertSelective(fundProduct);
    }

    @Override
    public FundProduct selectFundProductById(int id) {
        return fundProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteFundProduct(int id) {
        return fundProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateFundProduct(FundProduct fundProduct) {
        return fundProductMapper.updateByPrimaryKeySelective(fundProduct);
    }
}

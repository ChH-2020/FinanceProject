package com.finance.service.admin.finance;

import com.finance.pojo.others.FundProduct;

import java.util.List;

public interface FundProductService {

    List<FundProduct> selectALLFundProduct();
    int insertFundProduct(FundProduct fundProduct);
    FundProduct selectFundProductById(int id);
    int deleteFundProduct(int id);
    int updateFundProduct(FundProduct fundProduct);
}

package com.finance.controller.admin.finance;

import com.finance.common.Result;
import com.finance.pojo.others.FundProduct;
import com.finance.service.admin.finance.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class FundProductController {
    @Autowired
    FundProductService fundProductService;


    @RequestMapping("/addFundProduct")
    @ResponseBody
    public Result addFundProduct(FundProduct fundProduct){
        int i = fundProductService.insertFundProduct(fundProduct);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
    @RequestMapping("/getFundProductInfoById/{id}")
    @ResponseBody
    public Result getFundProductInfoById(@PathVariable("id") int id){
        FundProduct fundProduct = fundProductService.selectFundProductById(id);
        Result result = Result.success().add("fundProduct",fundProduct);
        return result;
    }

    @RequestMapping("/deleteFundProductById/{id}")
    @ResponseBody
    public Result deleteFundProductById(@PathVariable("id") int id){
        int i = fundProductService.deleteFundProduct(id);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @RequestMapping("/updateFundProduct/{id}")
    @ResponseBody
    public Result updateFundProduct(@PathVariable("id") int id, FundProduct fundProduct){
        fundProduct.setId(id);
        int i = fundProductService.updateFundProduct(fundProduct);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

}



package com.finance.controller.user.personal;

import com.finance.common.Result;
import com.finance.mapper.user.UserMapper;
import com.finance.pojo.others.Info;
import com.finance.service.user.personal.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class MyInfoController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InfoService infoService;

    @RequestMapping("/updateInfo/{id}")
    @ResponseBody
    public Result updateInfo(@PathVariable("id") Integer id){
        Info info = new Info();
        info.setId(id);
        info.setStatus(1);
        int i = infoService.updateMyInfo(info);
        if(i == 1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
    @RequestMapping("/deleteInfo/{id}")
    @ResponseBody
    public Result deleteInfo(@PathVariable("id") Integer id){
        int i = infoService.deleteMyInfoById(id);
        if(i==1){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

}

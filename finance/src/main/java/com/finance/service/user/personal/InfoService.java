package com.finance.service.user.personal;

import com.finance.pojo.others.Info;

import java.util.List;

public interface InfoService {
    public List<Info> selectAllMyInfo(Integer id);
    public int updateMyInfo(Info info);
    public int deleteMyInfoById(Integer id);
}

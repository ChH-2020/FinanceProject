package com.finance.service.user.personal;

import com.finance.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityService {
    public int updateUser(User user);
}

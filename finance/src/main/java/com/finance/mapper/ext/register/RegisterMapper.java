package com.finance.mapper.ext.register;

import com.finance.pojo.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface RegisterMapper {

    @Insert("insert into user(username,password) values(username=#{user.username},password=#{user.password})")
    public int insertUser(@Param("user") User user);
}

package com.finance.service.login;

import com.finance.pojo.admin.Admin;
import com.finance.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

import javax.xml.ws.soap.Addressing;

@Mapper
public interface LoginService {
    public int selectByName(String username);
    public Admin adminLogin(Admin admin);
    public Admin selectAdminByName(String username);
    public User userLogin(User user);
    public User selectUserByName(String username);
    public int addUser(User user);
    public void updateUserOnlineStatus(User user,String operator);
    public void updateAdminOnlineStatus(Admin admin, String operator);
    public int updateUserPassword(User user);
}

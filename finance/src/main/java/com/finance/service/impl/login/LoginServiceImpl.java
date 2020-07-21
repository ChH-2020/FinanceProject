package com.finance.service.impl.login;

import com.finance.mapper.admin.AdminMapper;
import com.finance.mapper.user.UserMapper;
import com.finance.pojo.admin.Admin;
import com.finance.pojo.admin.AdminExample;
import com.finance.pojo.user.User;
import com.finance.pojo.user.UserExample;
import com.finance.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int selectByName(String username) {
        UserExample userExample = new UserExample();
        AdminExample adminExample = new AdminExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        AdminExample.Criteria criteria1 = adminExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria1.andUsernameEqualTo(username);
        int i = userMapper.selectByExample(userExample).size();
        int j = adminMapper.selectByExample(adminExample).size();
        return i+j;
    }

    @Override
    public Admin adminLogin(Admin admin) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(admin.getUsername());
        criteria.andPasswordEqualTo(admin.getPassword());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if(admins.size()>=1){
            return admins.get(0);
        }else {
            System.out.println(admins);
            return null;
        }
    }

    @Override
    public Admin selectAdminByName(String username) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if(admins.size()>=1){
            return admins.get(0);
        }else {
            return null;
        }
    }

    @Override
    public User userLogin(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>=1){
            return users.get(0);
        }else {
            System.out.println(users);
            return null;
        }
    }

    @Override
    public User selectUserByName(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>=1){
            return users.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int addUser(User user) {
        System.out.println("添加用户"+user);
        return userMapper.insert(user);
    }

    @Override
    public void updateUserOnlineStatus(User user, String operator) {
        if(operator.equals("login")){
            user.setStatus(1);
        }else {
            user.setStatus(0);
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateAdminOnlineStatus(Admin admin, String operator) {
        if(operator.equals("login")){
            admin.setStatus(1);
        }else {
            admin.setStatus(0);
        }
        adminMapper.updateByPrimaryKey(admin);
    }

    @Override
    public int updateUserPassword(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

}

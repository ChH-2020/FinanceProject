package com.finance.service.user.userinfo;

import com.finance.pojo.admin.Admin;
import com.finance.pojo.others.Bankcard;
import com.finance.pojo.others.News;
import com.finance.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface UserInfoService {

    //用户信息管理
    public List<User> selectAllUser();
    public User selectUserById(int id);
    public int addUser(User user);
    public int updateUser(User user);
    public int deleteUserById(Integer id);

    //银行卡
    public List<Bankcard> selectAllBankcard();
    public Bankcard selectBankcardById(Integer id);
    public int updateBankcard(Bankcard bankcard);
    public int deleteBankcardById(Integer id);


    //News
    public List<News> selectAllNews();

    public int getUnReadInfoCountByUserId(Integer id);

//    //shiro
//    public User selectUserByName(String username);
//    public Admin selectAdminByName(String username);

}

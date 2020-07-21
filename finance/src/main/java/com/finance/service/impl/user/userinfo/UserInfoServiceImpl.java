package com.finance.service.impl.user.userinfo;

import com.finance.mapper.others.BankcardMapper;
import com.finance.mapper.others.InfoMapper;
import com.finance.mapper.others.NewsMapper;
import com.finance.mapper.user.UserMapper;
import com.finance.pojo.others.Bankcard;
import com.finance.pojo.others.BankcardExample;
import com.finance.pojo.others.InfoExample;
import com.finance.pojo.others.News;
import com.finance.pojo.user.User;
import com.finance.pojo.user.UserExample;
import com.finance.service.user.userinfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private BankcardMapper bankcardMapper;

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private InfoMapper infoMapper;

    @Override
    public List<User> selectAllUser() {
        System.out.println("-------查询全部--------");
        return userMapper.selectByExample(null);
    }

    @Override
    public User selectUserById(int id) {
        System.out.println("-------查询id--------"+ id);
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addUser(User user) {
        System.out.println("-------增加用户-----------");
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(User user) {
        System.out.println("--------更新信息ById----------");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(user.getId());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        System.out.println("-------删除用户---------"+id);
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Bankcard> selectAllBankcard() {
        System.out.println("-----查寻所有银行卡和持有人------");
        return bankcardMapper.selectByExample(null);
    }

    @Override
    public Bankcard selectBankcardById(Integer id) {
        System.out.println("-----查寻银行卡ID--------"+id);
        return bankcardMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateBankcard(Bankcard bankcard) {
        System.out.println("----编辑银行卡信息-----");
        BankcardExample bankcardExample = new BankcardExample();
        BankcardExample.Criteria criteria = bankcardExample.createCriteria();
        criteria.andIdEqualTo(bankcard.getId());
        return bankcardMapper.updateByPrimaryKeySelective(bankcard);
    }

    @Override
    public int deleteBankcardById(Integer id) {
        System.out.println("-----删除银行卡ID为-----"+ id);
        return bankcardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<News> selectAllNews() {
        return newsMapper.selectByExample(null);
    }

    @Override
    public int getUnReadInfoCountByUserId(Integer id) {
        InfoExample infoExample = new InfoExample();
        InfoExample.Criteria criteria = infoExample.createCriteria();
        criteria.andReceiveidEqualTo(id);
        criteria.andStatusEqualTo(0);
        return infoMapper.countByExample(infoExample);
    }
}

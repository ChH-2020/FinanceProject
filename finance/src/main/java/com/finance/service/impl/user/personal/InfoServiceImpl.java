package com.finance.service.impl.user.personal;

import com.finance.mapper.others.InfoMapper;
import com.finance.pojo.others.Info;
import com.finance.pojo.others.InfoExample;
import com.finance.pojo.others.LoanExample;
import com.finance.service.user.personal.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoMapper infoMapper;
    @Override
    public List<Info> selectAllMyInfo(Integer id) {
        InfoExample infoExample = new InfoExample();
        InfoExample.Criteria criteria = infoExample.createCriteria();
        criteria.andReceiveidEqualTo(id);
        infoExample.setOrderByClause("createtime desc");
        return infoMapper.selectByExample(infoExample);
    }

    @Override
    public int updateMyInfo(Info info) {
        return infoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public int deleteMyInfoById(Integer id) {
        return infoMapper.deleteByPrimaryKey(id);
    }
}

package com.finance.service.user.tools;

import com.finance.pojo.others.FlowOfFunds;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordService {
    public List<FlowOfFunds> selectAllRecordsByUserId(Integer id);
}

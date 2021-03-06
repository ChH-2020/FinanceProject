package com.finance.mapper.others;

import com.finance.pojo.others.Bankcard;
import com.finance.pojo.others.BankcardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankcardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int countByExample(BankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int deleteByExample(BankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int insert(Bankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int insertSelective(Bankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    List<Bankcard> selectByExample(BankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    Bankcard selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int updateByExampleSelective(@Param("record") Bankcard record, @Param("example") BankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int updateByExample(@Param("record") Bankcard record, @Param("example") BankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int updateByPrimaryKeySelective(Bankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bankcard
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    int updateByPrimaryKey(Bankcard record);
}
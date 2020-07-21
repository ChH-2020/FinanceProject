package com.finance.pojo.user;

import com.finance.pojo.others.TermFinancial;

import java.math.BigDecimal;
import java.util.Date;

public class UserTermFinancial {
    private TermFinancial termFinancial;

    @Override
    public String toString() {
        return "UserTermFinancial{" +
                "termFinancial=" + termFinancial +
                ", id=" + id +
                ", userId=" + userId +
                ", termId=" + termId +
                ", startTime=" + startTime +
                ", averYield=" + averYield +
                ", profit=" + profit +
                ", status=" + status +
                '}';
    }

    public TermFinancial getTermFinancial() {
        return termFinancial;
    }

    public void setTermFinancial(TermFinancial termFinancial) {
        this.termFinancial = termFinancial;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_term_financial.id
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_term_financial.userId
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_term_financial.termId
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    private Integer termId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_term_financial.startTime
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_term_financial.averYield
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    private BigDecimal averYield;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_term_financial.profit
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    private BigDecimal profit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_term_financial.status
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_term_financial.id
     *
     * @return the value of user_term_financial.id
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_term_financial.id
     *
     * @param id the value for user_term_financial.id
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_term_financial.userId
     *
     * @return the value of user_term_financial.userId
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_term_financial.userId
     *
     * @param userId the value for user_term_financial.userId
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_term_financial.termId
     *
     * @return the value of user_term_financial.termId
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public Integer getTermId() {
        return termId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_term_financial.termId
     *
     * @param termId the value for user_term_financial.termId
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_term_financial.startTime
     *
     * @return the value of user_term_financial.startTime
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_term_financial.startTime
     *
     * @param startTime the value for user_term_financial.startTime
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_term_financial.averYield
     *
     * @return the value of user_term_financial.averYield
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public BigDecimal getAverYield() {
        return averYield;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_term_financial.averYield
     *
     * @param averYield the value for user_term_financial.averYield
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public void setAverYield(BigDecimal averYield) {
        this.averYield = averYield;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_term_financial.profit
     *
     * @return the value of user_term_financial.profit
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_term_financial.profit
     *
     * @param profit the value for user_term_financial.profit
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_term_financial.status
     *
     * @return the value of user_term_financial.status
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_term_financial.status
     *
     * @param status the value for user_term_financial.status
     *
     * @mbggenerated Wed Jul 15 12:38:52 CST 2020
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
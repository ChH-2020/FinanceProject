package com.finance.pojo.others;

import java.math.BigDecimal;

public class PayMoney {
    @Override
    public String toString() {
        return "PayMoney{" +
                "id=" + id +
                ", monthmoney=" + monthmoney +
                ", autointo=" + autointo +
                ", type=" + type +
                ", investerm='" + investerm + '\'' +
                '}';
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_money.id
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_money.monthMoney
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    private BigDecimal monthmoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_money.autoInto
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    private Integer autointo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_money.type
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_money.invesTerm
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    private String investerm;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_money.id
     *
     * @return the value of pay_money.id
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_money.id
     *
     * @param id the value for pay_money.id
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_money.monthMoney
     *
     * @return the value of pay_money.monthMoney
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public BigDecimal getMonthmoney() {
        return monthmoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_money.monthMoney
     *
     * @param monthmoney the value for pay_money.monthMoney
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public void setMonthmoney(BigDecimal monthmoney) {
        this.monthmoney = monthmoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_money.autoInto
     *
     * @return the value of pay_money.autoInto
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public Integer getAutointo() {
        return autointo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_money.autoInto
     *
     * @param autointo the value for pay_money.autoInto
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public void setAutointo(Integer autointo) {
        this.autointo = autointo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_money.type
     *
     * @return the value of pay_money.type
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_money.type
     *
     * @param type the value for pay_money.type
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_money.invesTerm
     *
     * @return the value of pay_money.invesTerm
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public String getInvesterm() {
        return investerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_money.invesTerm
     *
     * @param investerm the value for pay_money.invesTerm
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public void setInvesterm(String investerm) {
        this.investerm = investerm;
    }
}
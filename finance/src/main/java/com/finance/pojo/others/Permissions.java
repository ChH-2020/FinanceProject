package com.finance.pojo.others;

public class Permissions {
    @Override
    public String toString() {
        return "Permissions{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                '}';
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permissions.id
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permissions.permission
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    private String permission;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permissions.id
     *
     * @return the value of permissions.id
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permissions.id
     *
     * @param id the value for permissions.id
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permissions.permission
     *
     * @return the value of permissions.permission
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public String getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permissions.permission
     *
     * @param permission the value for permissions.permission
     *
     * @mbggenerated Mon Jul 13 12:46:18 CST 2020
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }
}
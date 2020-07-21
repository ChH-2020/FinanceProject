package com.finance.pojo.admin;

import com.finance.pojo.others.Permissions;

public class AdminPermissions {

    private Permissions permissions;

    @Override
    public String toString() {
        return "AdminPermissions{" +
                "permissions=" + permissions +
                ", id=" + id +
                ", adminid=" + adminid +
                ", permissionid=" + permissionid +
                '}';
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_permissions.id
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_permissions.adminId
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    private Integer adminid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_permissions.permissionId
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    private Integer permissionid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_permissions.id
     *
     * @return the value of admin_permissions.id
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_permissions.id
     *
     * @param id the value for admin_permissions.id
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_permissions.adminId
     *
     * @return the value of admin_permissions.adminId
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    public Integer getAdminid() {
        return adminid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_permissions.adminId
     *
     * @param adminid the value for admin_permissions.adminId
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_permissions.permissionId
     *
     * @return the value of admin_permissions.permissionId
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    public Integer getPermissionid() {
        return permissionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_permissions.permissionId
     *
     * @param permissionid the value for admin_permissions.permissionId
     *
     * @mbggenerated Mon Jul 13 12:47:04 CST 2020
     */
    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}
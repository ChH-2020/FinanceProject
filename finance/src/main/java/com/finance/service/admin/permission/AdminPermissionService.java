package com.finance.service.admin.permission;

import com.finance.pojo.admin.AdminPermissions;

import java.util.List;

public interface AdminPermissionService {
    public List<AdminPermissions> selectAdminPermissionsById(Integer id);
    public boolean updateAdminPermissionsById(Integer id,String[] permissions);
}

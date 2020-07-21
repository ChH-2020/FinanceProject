package com.finance.service.admin.permission;

import com.finance.pojo.user.UserPermissions;

import java.util.List;

public interface UserPermissionService {
    public List<UserPermissions> selectAllUserPermissions();
    public List<UserPermissions> selectUserPermissionsById(Integer id);
    public boolean updateUserPermissionsById(Integer id,String[] permissions);
}

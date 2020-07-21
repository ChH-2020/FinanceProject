package com.finance.service.impl.admin.permission;

import com.finance.mapper.admin.AdminPermissionsMapper;
import com.finance.mapper.others.PermissionsMapper;
import com.finance.pojo.admin.AdminPermissions;
import com.finance.pojo.admin.AdminPermissionsExample;
import com.finance.pojo.others.Permissions;
import com.finance.pojo.others.PermissionsExample;
import com.finance.service.admin.permission.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {


    @Autowired
    private AdminPermissionsMapper adminPermissionsMapper;
    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public List<AdminPermissions> selectAdminPermissionsById(Integer id) {
        AdminPermissionsExample adminPermissionsExample = new AdminPermissionsExample();
        AdminPermissionsExample.Criteria criteria = adminPermissionsExample.createCriteria();
        criteria.andAdminidEqualTo(id);
        return adminPermissionsMapper.selectByExample(adminPermissionsExample);
    }
    @Override
    public boolean updateAdminPermissionsById(Integer id, String[] permissions) {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list,permissions);
        boolean result = true;
        List<AdminPermissions> adminPermissionsList = this.selectAdminPermissionsById(id);

        System.out.println("zhe是要更新的权限"+list);
        System.out.println("zhe是查询得到的现有的权限"+adminPermissionsList);

        for(AdminPermissions ap: adminPermissionsList){
            if(!list.contains(ap.getPermissions().getPermission())){//当前更新后的权限不包含以前查出来的就删除
                int i = adminPermissionsMapper.deleteByPrimaryKey(ap.getId());
                if(i==0){//只要有一次执行失败就失败
                    result = false;
                    break;
                }
            }else {
                if(list.isEmpty()){
                    break;
                }else {
//                    System.out.println("移除"+ap.getPermissions().getPermission());

                    int i = list.indexOf(ap.getPermissions().getPermission());
//                    System.out.println(i+"是当前的\n"+list);
                    list.remove(i);
                }
            }

        }
        if(!list.isEmpty()){//有新的权限被赋予了
            for(String np: list){
                PermissionsExample permissionsExample = new PermissionsExample();
                PermissionsExample.Criteria criteria = permissionsExample.createCriteria();
                criteria.andPermissionEqualTo(np);
                List<Permissions> permissionsList1 = permissionsMapper.selectByExample(permissionsExample);
                if(permissionsList1.isEmpty()){
                    break;
                }
                Permissions permissions1 = permissionsList1.get(0);

                AdminPermissions adminPermissions = new AdminPermissions();
                adminPermissions.setPermissionid(permissions1.getId());
                adminPermissions.setPermissions(permissions1);
                adminPermissions.setAdminid(1);
                int i = adminPermissionsMapper.insert(adminPermissions);
                if(i==0){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}

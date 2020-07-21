package com.finance.service.impl.admin.permission;

import com.finance.mapper.others.PermissionsMapper;
import com.finance.mapper.user.UserPermissionsMapper;
import com.finance.pojo.others.Permissions;
import com.finance.pojo.others.PermissionsExample;
import com.finance.pojo.user.UserPermissions;
import com.finance.pojo.user.UserPermissionsExample;
import com.finance.service.admin.permission.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionsMapper userPermissionsMapper;
    @Autowired
    private PermissionsMapper permissionsMapper;
    @Override
    public List<UserPermissions> selectAllUserPermissions() {
        return userPermissionsMapper.selectByExample(null);
    }

    @Override
    public List<UserPermissions> selectUserPermissionsById(Integer id) {
        UserPermissionsExample userPermissionsExample = new UserPermissionsExample();
        UserPermissionsExample.Criteria criteria = userPermissionsExample.createCriteria();
        criteria.andUseridEqualTo(id);
        return userPermissionsMapper.selectByExample(userPermissionsExample);
    }

    @Override
    public boolean updateUserPermissionsById(Integer id,String[] permissions) {
//        List<String> list = Arrays.asList(permissions);//这种方法会报错
        List<String> list = new ArrayList<String>();
        Collections.addAll(list,permissions);
        boolean result = true;
        List<UserPermissions> userPermissionsList = this.selectUserPermissionsById(id);

//        System.out.println("zhe是要更新的权限"+list);
//        System.out.println("zhe是查询得到的现有的权限"+userPermissionsList);

        for(UserPermissions up: userPermissionsList){
            if(!list.contains(up.getPermissions().getPermission())){//当前更新后的权限不包含以前查出来的就删除
                int i = userPermissionsMapper.deleteByPrimaryKey(up.getId());
                if(i==0){//只要有一次执行失败就失败
                    result = false;
                    break;
                }
            }else {
                if(list.isEmpty()){
                    break;
                }else {
//                System.out.println("移除"+up.getPermissions().getPermission());

                    int i = list.indexOf(up.getPermissions().getPermission());
//                System.out.println(i+"是当前的\n"+list);
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

                UserPermissions userPermissions = new UserPermissions();
                userPermissions.setPermissionid(permissions1.getId());
                userPermissions.setPermissions(permissions1);
                userPermissions.setUserid(1);
                int i = userPermissionsMapper.insert(userPermissions);
                if(i==0){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}

package com.finance.controller.admin.permission;

import com.finance.common.Result;
import com.finance.service.admin.permission.AdminPermissionService;
import com.finance.service.admin.permission.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin")
public class PermissionMethodsController {
    @Autowired
    private UserPermissionService userPermissionService;
    @Autowired
    private AdminPermissionService adminPermissionService;

    /**
     * 接收Permissions的String,然后实现权限的更新
     * @return
     */
    @RequestMapping("/updateUserPermissions")
    @ResponseBody
    public Result updateUserPermissions(@RequestParam("userPermissions") String strings){

        System.out.println("-----更新用户权限信息-----");
        System.out.println(strings);
        String[] permissions = strings.split(";");
        System.out.println(permissions[0]+permissions[permissions.length-1]);
        if(userPermissionService.updateUserPermissionsById(1,permissions)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @RequestMapping("/updateAdminPermissions")
    @ResponseBody
    public Result updateAdminPermissions(@RequestParam("adminPermissions") String strings){

        System.out.println("-----更新管理员权限信息-----");
        System.out.println(strings);
        String[] permissions = strings.split(";");
        System.out.println(permissions[0]+permissions[permissions.length-1]);
        if(adminPermissionService.updateAdminPermissionsById(1,permissions)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}

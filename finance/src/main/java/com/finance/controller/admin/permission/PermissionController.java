package com.finance.controller.admin.permission;

import com.finance.pojo.admin.AdminPermissions;
import com.finance.pojo.user.UserPermissions;
import com.finance.service.admin.permission.AdminPermissionService;
import com.finance.service.admin.permission.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("admin/permission/")
public class PermissionController {

    @Autowired
    private UserPermissionService userPermissionService;
    @Autowired
    private AdminPermissionService adminPermissionService;

    @RequestMapping("/toUserPermissions.html")
    public String toUserPermissions(Model model){

        List<UserPermissions> userPermissionsList = userPermissionService.selectUserPermissionsById(1);
        List<String> permissionsList = new LinkedList<>();
        for(UserPermissions up:userPermissionsList){
            permissionsList.add(up.getPermissions().getPermission());
        }
        System.out.println("----用户权限-----");
        model.addAttribute("permissionsList",permissionsList);
        model.addAttribute("activeUrl1","permissionActive");
        model.addAttribute("activeUrl2","userPermissionsActive");

        return "admin/permission/userpermissions";
    }

    @RequestMapping("/toAdminPermissions.html")
    public String toAdminPermissions(Model model){

        List<AdminPermissions> adminPermissionsList = adminPermissionService.selectAdminPermissionsById(1);
        List<String> permissionsList = new LinkedList<>();
        for(AdminPermissions ap: adminPermissionsList){
            permissionsList.add(ap.getPermissions().getPermission());
        }
        System.out.println("----用户权限-----");

        model.addAttribute("permissionsList",permissionsList);
        model.addAttribute("activeUrl1","permissionActive");
        model.addAttribute("activeUrl2","adminPermissionsActive");

        return "admin/permission/adminpermissions";
    }



}

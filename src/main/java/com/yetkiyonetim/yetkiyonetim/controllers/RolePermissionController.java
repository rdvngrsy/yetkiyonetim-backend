package com.yetkiyonetim.yetkiyonetim.controllers;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.RolePermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.CreateRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.DeleteRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public List<GetRolePermissionListResponse> getAllRolePermissions() {
        return rolePermissionService.getAllRolePermissions();
    }

    @GetMapping("/role/{roleId}")
    public List<GetRolePermissionResponse> getPermissionsByRoleId(@PathVariable Long roleId) {
        return rolePermissionService.getPermissionsByRoleId(roleId);
    }

    @GetMapping("/permission/{permissionId}")
    public List<GetRolePermissionResponse> getRolesByPermissionId(@PathVariable Long permissionId) {
        return rolePermissionService.getRolesByPermissionId(permissionId);
    }

    @PostMapping
    public void createRolePermission(@RequestBody CreateRolePermissionRequest createRolePermissionRequest) {
        rolePermissionService.createRolePermission(createRolePermissionRequest);
    }

    @DeleteMapping
    public void deleteRolePermission(@RequestBody DeleteRolePermissionRequest deleteRolePermissionRequest) {
        rolePermissionService.deleteRolePermission(deleteRolePermissionRequest);
    }
}
package com.yetkiyonetim.yetkiyonetim.controllers;

import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserRoleService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.CreateUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.DeleteUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole.GetUserRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole.GetUserRoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@CrossOrigin
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public List<GetUserRoleListResponse> getAllUserRoles() {
        return userRoleService.getAllUserRoles();
    }

    @GetMapping("/user/{userId}")
    public List<GetUserRoleResponse> getRolesByUserId(@RequestParam Long userId) {
        return userRoleService.getRolesByUserId(userId);
    }

    @GetMapping("/role/{roleId}")
    public List<GetUserRoleResponse> getUsersByRoleId(@PathVariable Long roleId) {
        return userRoleService.getUsersByRoleId(roleId);
    }

    @PostMapping
    public void createUserRole(@RequestBody CreateUserRoleRequest createUserRoleRequest) {
        userRoleService.createUserRole(createUserRoleRequest);
    }

    @DeleteMapping
    public void deleteUserRole(@RequestBody DeleteUserRoleRequest deleteUserRoleRequest) {
        userRoleService.deleteUserRole(deleteUserRoleRequest);
    }
}

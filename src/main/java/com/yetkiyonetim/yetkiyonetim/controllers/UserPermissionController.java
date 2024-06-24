package com.yetkiyonetim.yetkiyonetim.controllers;

import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserPermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userPermission.CreateUserPermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userPermission.GetUserPermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userPermission.GetUserPermissionResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user-permissions")
@CrossOrigin
public class UserPermissionController {

    private UserPermissionService userPermissionService;

    @GetMapping
    public List<GetUserPermissionListResponse> getAllUserPermissions() {
        return userPermissionService.getAllUserPermissions();
    }

    @GetMapping("/permission/{userId}")
    public List<GetUserPermissionResponse> getPermissionsByUserId(@PathVariable Long userId) {
        return userPermissionService.getPermissionsByUserId(userId);
    }

    @GetMapping("/permission/{permissionId}")
    public List<GetUserPermissionResponse> getUserByPermissionId(@PathVariable Long permissionId) {
        return userPermissionService.getUserByPermissionId(permissionId);
    }

    @PostMapping
    public void save(@RequestBody CreateUserPermissionRequest request) {
        userPermissionService.save(request);
    }

    @DeleteMapping
    public void deleteUserPermission(@PathVariable Long userId, @PathVariable Long permissionId) {
        userPermissionService.deleteByUserIdAndPermissionId(userId, permissionId);
    }
}

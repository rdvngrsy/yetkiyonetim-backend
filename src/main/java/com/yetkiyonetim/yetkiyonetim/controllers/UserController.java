package com.yetkiyonetim.yetkiyonetim.controllers;


import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.CreateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.UpdateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private UserService permissionService;

    @GetMapping
    public List<GetUserListResponse> getAllUsers() {
        return permissionService.getAllUsers();
    }

    @GetMapping("/{id}")
    public GetUserResponse getUserById(@PathVariable Long id) {
        return permissionService.getUserById(id);
    }

    @PutMapping
    public void updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        permissionService.updateUser(updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable Long id) {
        permissionService.deleteUser(id);
    }
}

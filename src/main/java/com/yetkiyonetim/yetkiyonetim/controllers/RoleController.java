package com.yetkiyonetim.yetkiyonetim.controllers;

import com.yetkiyonetim.yetkiyonetim.services.abstracts.RoleService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.CreateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.UpdateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
@CrossOrigin
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public List<GetRoleListResponse> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public GetRoleResponse getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }


    @PostMapping
    public  void createRole(@RequestBody @Valid CreateRoleRequest createRoleRequest) {
        roleService.createRole(createRoleRequest);
    }

    @PutMapping
    public void updateRole(@RequestBody @Valid UpdateRoleRequest updateRoleRequest) {
        roleService.updateRole(updateRoleRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
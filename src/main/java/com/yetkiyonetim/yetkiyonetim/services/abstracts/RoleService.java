package com.yetkiyonetim.yetkiyonetim.services.abstracts;


import com.yetkiyonetim.yetkiyonetim.entities.concretes.Role;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.CreateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.UpdateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleResponse;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<GetRoleListResponse> getAllRoles();
    GetRoleResponse getRoleById(Long id);
    void createRole(CreateRoleRequest createRoleRequest);
    void updateRole(UpdateRoleRequest updateRoleRequest);
    void deleteRole(Long id);
}

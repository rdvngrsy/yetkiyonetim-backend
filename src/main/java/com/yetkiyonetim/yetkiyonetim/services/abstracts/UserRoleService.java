package com.yetkiyonetim.yetkiyonetim.services.abstracts;


import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserRoleId;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.CreateUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.DeleteUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.UpdateUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole.GetUserRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole.GetUserRoleResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserRoleService {
    List<GetUserRoleListResponse> getAllUserRoles();
    List<GetUserRoleResponse> getRolesByUserId(Long userId);
    List<GetUserRoleResponse> getUsersByRoleId(Long roleId);
    void createUserRole(CreateUserRoleRequest createUserRoleRequest);
//    void updateUserRole(UpdateUserRoleRequest updateUserRoleRequest);
    void deleteUserRole(DeleteUserRoleRequest deleteUserRoleRequest);
}

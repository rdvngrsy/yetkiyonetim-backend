package com.yetkiyonetim.yetkiyonetim.services.concretes;


import com.yetkiyonetim.yetkiyonetim.businessRules.UserPermissionBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper.PermissionMapper;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper.UserMapper;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper.UserPermissionMapper;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.modelMapper.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserPermissionId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.RolePermission;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserPermission;
import com.yetkiyonetim.yetkiyonetim.repositories.UserPermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.PermissionService;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserPermissionService;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userPermission.CreateUserPermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userPermission.GetUserPermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userPermission.GetUserPermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPermissionServiceImpl implements UserPermissionService {
    private final UserPermissionBusinessRules userPermissionBusinessRules;

    private final UserPermissionRepository userPermissionRepository;

    private final UserPermissionMapper userPermissionMapper;

    private final UserService userService;

  private final ModelMapperService modelMapperService;

    @Override
    public List<GetUserPermissionListResponse> getAllUserPermissions() {
        List<UserPermission> userPermissions = userPermissionRepository.findAll();
        return userPermissions.stream()
                .map(userPermission -> modelMapperService.forResponse().map(userPermission, GetUserPermissionListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetUserPermissionResponse> getPermissionsByUserId(Long userId) {
        userPermissionBusinessRules.checkIfUserPermissionExistsByUserId(userId);

        List<UserPermission> userPermissions = userPermissionRepository.findByUserId(userId);
        return userPermissions.stream()
                .map(userPermission -> modelMapperService.forResponse().map(userPermission, GetUserPermissionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetUserPermissionResponse> getUserByPermissionId(Long permissionId) {
        userPermissionBusinessRules.checkIfUserPermissionExistsByPermissionId(permissionId);

        List<UserPermission> userPermissions = userPermissionRepository.findByPermissionId(permissionId);
        return userPermissions.stream()
                .map(userPermission -> modelMapperService.forResponse().map(userPermission, GetUserPermissionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(CreateUserPermissionRequest userPermissionRequest) {
        userPermissionBusinessRules.checkIfUserPermissionExists(userPermissionRequest.getUser().getId(), userPermissionRequest.getPermission().getId());

        UserPermission userPermission = new UserPermission();
        userPermission.setUser(userPermissionRequest.getUser());
        userPermission.setPermission(userPermissionRequest.getPermission());
        userPermissionRepository.save(userPermission);

    }
    @Override
    public void deleteByUserIdAndPermissionId(Long userId, Long permissionId) {
        userPermissionBusinessRules.checkForDeleteIfUserPermissionExists(userId, permissionId);
        userPermissionRepository.deleteByUserIdAndPermissionId(userId, permissionId);
    }
}

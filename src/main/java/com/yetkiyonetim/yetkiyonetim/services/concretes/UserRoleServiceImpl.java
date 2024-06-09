package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.businessRules.UserRoleBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserRoleId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.RolePermission;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserRole;
import com.yetkiyonetim.yetkiyonetim.repositories.UserRoleRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.RolePermissionService;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserPermissionService;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserRoleService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userPermission.CreateUserPermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.CreateUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.DeleteUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole.GetUserRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole.GetUserRoleResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleBusinessRules userRoleBusinessRules;

    private final UserRoleRepository userRoleRepository;

    private final RolePermissionService rolePermissionService;

    private final UserPermissionService userPermissionService;

    private final ModelMapperService modelMapperService;

    @Override
    public List<GetUserRoleListResponse> getAllUserRoles() {
        List<UserRole> userRoleList = userRoleRepository.findAll();
        return userRoleList.stream()
                .map(userRole -> modelMapperService.forResponse().map(userRole, GetUserRoleListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetUserRoleResponse> getRolesByUserId(Long userId) {
        userRoleBusinessRules.checkIfUserRoleExistsByUserId(userId);
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        return userRoles.stream()
                .map(userRole -> modelMapperService.forResponse().map(userRole, GetUserRoleResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetUserRoleResponse> getUsersByRoleId(Long roleId) {
        userRoleBusinessRules.checkIfUserRoleExistsByRoleId(roleId);
        List<UserRole> userRoles = userRoleRepository.findByRoleId(roleId);
        return userRoles.stream()
                .map(userRole -> modelMapperService.forResponse().map(userRole, GetUserRoleResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createUserRole(CreateUserRoleRequest createUserRoleRequest) {
        userRoleBusinessRules.checkIfUserRoleExists(createUserRoleRequest.getUserId(), createUserRoleRequest.getRoleId());
        UserRole userRole = modelMapperService.forRequest().map(createUserRoleRequest, UserRole.class);
        userRoleRepository.save(userRole);

        // User'a Role atandığında, otomatik olarak UserPermission ekleyin
        List<RolePermission> rolePermissions = rolePermissionService.findByRoleId(createUserRoleRequest.getRoleId());
        for (RolePermission rolePermission : rolePermissions) {
            CreateUserPermissionRequest userPermissionRequest = new CreateUserPermissionRequest();
            userPermissionRequest.setUser(userRole.getUser());
            userPermissionRequest.setPermission(rolePermission.getPermission());
            userPermissionService.save(userPermissionRequest);
        }
    }

//    @Override
//    @Transactional
//    public void updateUserRole(UpdateUserRoleRequest updateUserRoleRequest) {
//        UserRoleId userRoleId = new UserRoleId(updateUserRoleRequest.getUserId(), updateUserRoleRequest.getOldRoleId());
//        UserRole userRole = userRoleRepository.findById(userRoleId)
//                .orElseThrow(() -> new RuntimeException("UserRole not found"));
//
//        // Eski Role'un Permission'larını kaldırın
//        List<RolePermission> oldRolePermissions = rolePermissionService.findByRoleId(updateUserRoleRequest.getOldRoleId());
//        for (RolePermission rolePermission : oldRolePermissions) {
//            userPermissionService.deleteByUserIdAndPermissionId(updateUserRoleRequest.getUserId(), rolePermission.getPermission().getId());
//        }
//
//        // Yeni Role'u atayın
//        userRole.setRole(new Role(updateUserRoleRequest.getNewRoleId()));
//        userRoleRepository.save(userRole);
//
//        // Yeni Role'un Permission'larını ekleyin
//        List<RolePermission> newRolePermissions = rolePermissionService.findByRoleId(updateUserRoleRequest.getNewRoleId());
//        for (RolePermission rolePermission : newRolePermissions) {
//            UserPermission userPermission = new UserPermission();
//            userPermission.setUser(userRole.getUser());
//            userPermission.setPermission(rolePermission.getPermission());
//            userPermissionService.save(userPermission);
//        }
//    }

    @Override
    @Transactional
    public void deleteUserRole(DeleteUserRoleRequest deleteUserRoleRequest) {
        userRoleBusinessRules.checkForDeleteIfUserRoleExists(deleteUserRoleRequest.getUserId(), deleteUserRoleRequest.getRoleId());

        UserRole userRole = userRoleRepository.findById(new UserRoleId(deleteUserRoleRequest.getUserId(), deleteUserRoleRequest.getRoleId()))
                .orElseThrow(() -> new RuntimeException("UserRole not found"));

        // Role'un Permission'larını kaldırın
        List<RolePermission> rolePermissions = rolePermissionService.findByRoleId(deleteUserRoleRequest.getRoleId());
        for (RolePermission rolePermission : rolePermissions) {
            userPermissionService.deleteByUserIdAndPermissionId(deleteUserRoleRequest.getUserId(), rolePermission.getPermission().getId());
        }

        userRoleRepository.delete(userRole);
    }

}


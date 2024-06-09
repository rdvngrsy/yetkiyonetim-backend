package com.yetkiyonetim.yetkiyonetim.businessRules;


import com.yetkiyonetim.yetkiyonetim.core.utilities.exceptions.BusinessException;
import com.yetkiyonetim.yetkiyonetim.repositories.RolePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolePermissionBusinessRules {

    private final RolePermissionRepository rolePermissionRepository;

    public void checkIfRolePermissionExists(Long roleId, Long permissionId) {
        if (rolePermissionRepository.existsByRoleIdAndPermissionId(roleId, permissionId)) {
            throw new BusinessException("RolePermission not found.");
        }
    }

    public void checkForDeleteIfRolePermissionExists(Long roleId, Long permissionId) {
        if (!rolePermissionRepository.existsByRoleIdAndPermissionId(roleId, permissionId)) {
            throw new BusinessException("RolePermission not found.");
        }
    }

    public void checkIfRolePermissionExistsByRoleId(Long roleId) {
        if (!rolePermissionRepository.existsByRoleId(roleId)) {
            throw new BusinessException("RolePermission not found.");
        }
    }

    public void checkIfRolePermissionExistsByPermissionId(Long permissionId) {
        if (!rolePermissionRepository.existsByPermissionId(permissionId)) {
            throw new BusinessException("RolePermission not found.");
        }
    }
}

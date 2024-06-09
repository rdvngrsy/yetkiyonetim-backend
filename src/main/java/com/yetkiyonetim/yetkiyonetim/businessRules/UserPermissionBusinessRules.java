package com.yetkiyonetim.yetkiyonetim.businessRules;

import com.yetkiyonetim.yetkiyonetim.core.utilities.exceptions.BusinessException;
import com.yetkiyonetim.yetkiyonetim.repositories.UserPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPermissionBusinessRules {

        private final UserPermissionRepository userPermissionRepository;

        public void checkIfUserPermissionExists(Long userId, Long permissionId) {
            if (userPermissionRepository.existsByUserIdAndPermissionId(userId, permissionId)) {
                throw new BusinessException("UserPermission not found.");
            }
        }

    public void checkForDeleteIfUserPermissionExists(Long userId, Long permissionId) {
        if (!userPermissionRepository.existsByUserIdAndPermissionId(userId, permissionId)) {
            throw new BusinessException("UserPermission not found.");
        }
    }


    public void checkIfUserPermissionExistsByUserId(Long userId) {
            if (!userPermissionRepository.existsByUserId(userId)) {
                throw new BusinessException("UserPermission not found.");
            }
        }

        public void checkIfUserPermissionExistsByPermissionId(Long permissionId) {
            if (!userPermissionRepository.existsByPermissionId(permissionId)) {
                throw new BusinessException("UserPermission not found.");
            }
        }
}

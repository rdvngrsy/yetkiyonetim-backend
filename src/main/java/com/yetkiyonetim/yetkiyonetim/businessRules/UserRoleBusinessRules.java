package com.yetkiyonetim.yetkiyonetim.businessRules;

import com.yetkiyonetim.yetkiyonetim.core.utilities.exceptions.BusinessException;
import com.yetkiyonetim.yetkiyonetim.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleBusinessRules {

    private final UserRoleRepository userRoleRepository;

    public void checkIfUserRoleExists(Long userId, Long roleId) {
        if (userRoleRepository.existsByUserIdAndRoleId(userId, roleId)) {
            throw new BusinessException("UserRole already exists.");
        }
    }

    public void checkForDeleteIfUserRoleExists(Long userId, Long roleId) {
        if (!userRoleRepository.existsByUserIdAndRoleId(userId, roleId)) {
            throw new BusinessException("UserRole not found.");
        }
    }

    public void checkIfUserRoleExistsByUserId(Long userId) {
        if (!userRoleRepository.existsByUserId(userId)) {
            throw new BusinessException("User not found.");
        }
    }

    public void checkIfUserRoleExistsByRoleId(Long roleId) {
        if (!userRoleRepository.existsByRoleId(roleId)) {
            throw new BusinessException("Role not found.");
        }
    }
}

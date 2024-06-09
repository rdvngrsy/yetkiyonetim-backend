package com.yetkiyonetim.yetkiyonetim.businessRules;


import com.yetkiyonetim.yetkiyonetim.core.utilities.exceptions.BusinessException;
import com.yetkiyonetim.yetkiyonetim.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionBusinessRules {

    private final PermissionRepository permissionRepository;

    public void checkIfPermissionNameExists(String name) {
        if (permissionRepository.existsByName(name)) {
            throw new BusinessException("Permission name already exists.");
        }
    }

    public void checkIfPermissionExists(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new BusinessException("Permission not found.");
        }
    }
}
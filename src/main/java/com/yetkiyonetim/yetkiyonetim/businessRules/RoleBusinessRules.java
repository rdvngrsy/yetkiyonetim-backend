package com.yetkiyonetim.yetkiyonetim.businessRules;

import com.yetkiyonetim.yetkiyonetim.core.utilities.exceptions.BusinessException;
import com.yetkiyonetim.yetkiyonetim.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleBusinessRules {

        private final RoleRepository roleRepository;

        public void checkIfRoleNameExists(String name) {
            if (roleRepository.existsByName(name)) {
                throw new BusinessException("Role name already exists.");
            }
        }

        public void checkIfRoleExists(Long id) {
            if (!roleRepository.existsById(id)) {
                throw new BusinessException("Role not found.");
            }
        }
}

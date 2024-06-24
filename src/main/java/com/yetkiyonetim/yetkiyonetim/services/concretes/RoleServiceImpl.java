package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.businessRules.RoleBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper.RoleMapper;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.modelMapper.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.Role;
import com.yetkiyonetim.yetkiyonetim.repositories.RoleRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.RoleService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.CreateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.UpdateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleBusinessRules roleBusinessRules;
    private final RoleMapper roleMapper; // RoleMapper enjekte edildi

    @Override
    public List<GetRoleListResponse> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> roleMapper.roleToGetRoleListResponse(role))
                .collect(Collectors.toList());
    }

    @Override
    public GetRoleResponse getRoleById(Long id) {
        roleBusinessRules.checkIfRoleExists(id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return roleMapper.roleToGetRoleResponse(role); // MapStruct kullanımı
    }

    @Override
    public void createRole(CreateRoleRequest createRoleRequest) {
        roleBusinessRules.checkIfRoleNameExists(createRoleRequest.getName());

        Role role = roleMapper.createRoleRequestToRole(createRoleRequest); // MapStruct kullanımı
        roleRepository.save(role);
    }

    @Override
    public void updateRole(UpdateRoleRequest updateRoleRequest) {
        roleBusinessRules.checkIfRoleExists(updateRoleRequest.getId());

        Role existingRole = roleMapper.updateRoleRequestToRole(updateRoleRequest); // MapStruct kullanımı
        roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long id) {
        roleBusinessRules.checkIfRoleExists(id);

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(role);
    }
}


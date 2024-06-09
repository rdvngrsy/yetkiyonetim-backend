package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.businessRules.RoleBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.Role;
import com.yetkiyonetim.yetkiyonetim.repositories.RoleRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.RoleService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.CreateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.UpdateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleBusinessRules roleBusinessRules;

    private final RoleRepository roleRepository;

    private final ModelMapperService modelMapperService;

    @Override
    public List<GetRoleListResponse> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream()
                .map(role -> modelMapperService.forResponse().map(role, GetRoleListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetRoleResponse getRoleById(Long id) {
        roleBusinessRules.checkIfRoleExists(id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return modelMapperService.forResponse().map(role, GetRoleResponse.class);
    }

    @Override
    public void createRole(CreateRoleRequest createRoleRequest) {
        roleBusinessRules.checkIfRoleNameExists(createRoleRequest.getRoleName());

        Role role = modelMapperService.forRequest().map(createRoleRequest, Role.class);
        roleRepository.save(role);
    }

    @Override
    public void updateRole(UpdateRoleRequest updateRoleRequest) {
        roleBusinessRules.checkIfRoleExists(updateRoleRequest.getId());

        Role role = roleRepository.findById(updateRoleRequest.getId())
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + updateRoleRequest.getId()));
        role.setName(updateRoleRequest.getRoleName());
        role.setDescription(updateRoleRequest.getDescription());
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleBusinessRules.checkIfRoleExists(id);

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(role);
    }

}

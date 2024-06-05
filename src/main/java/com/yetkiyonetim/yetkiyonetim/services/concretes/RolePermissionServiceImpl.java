package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.RolePermissionId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.RolePermission;
import com.yetkiyonetim.yetkiyonetim.repositories.RolePermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.RolePermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.CreateRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.UpdateRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public List<GetRolePermissionListResponse> getAllRolePermissions() {
        List<RolePermission> rolePermissionList = rolePermissionRepository.findAll();
        return rolePermissionList.stream()
                .map(rolePermission -> modelMapperService.forResponse().map(rolePermission, GetRolePermissionListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetRolePermissionResponse getRolePermissionById(RolePermissionId id) {
        RolePermission rolePermission = rolePermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolePermission not found with id: " + id));
        return modelMapperService.forResponse().map(rolePermission, GetRolePermissionResponse.class);
    }

    @Override
    public void createRolePermission(CreateRolePermissionRequest createRolePermissionRequest) {
        RolePermission rolePermission = modelMapperService.forRequest().map(createRolePermissionRequest, RolePermission.class);
        rolePermissionRepository.save(rolePermission);
    }

    @Override
    public void updateRolePermission(UpdateRolePermissionRequest updateRolePermissionRequest) {
        RolePermissionId id = new RolePermissionId(updateRolePermissionRequest.getRoleId(), updateRolePermissionRequest.getPermissionId());
        RolePermission rolePermission = rolePermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolePermission not found with id: " + id));
        // Update fields as necessary
        rolePermissionRepository.save(rolePermission);
    }

    @Override
    public void deleteRolePermission(RolePermissionId id) {
        RolePermission rolePermission = rolePermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolePermission not found with id: " + id));
        rolePermissionRepository.delete(rolePermission);
    }
}


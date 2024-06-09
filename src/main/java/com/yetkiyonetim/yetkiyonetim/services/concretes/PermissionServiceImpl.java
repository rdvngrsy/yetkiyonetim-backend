package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.businessRules.PermissionBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import com.yetkiyonetim.yetkiyonetim.repositories.PermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.PermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.CreatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.UpdatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    private final PermissionBusinessRules permissionBusinessRules;

    private final ModelMapperService modelMapperService;

    @Override
    public List<GetPermissionListResponse> getAllPermissions() {
        List<Permission> permissionList = permissionRepository.findAll();
        return permissionList.stream()
                .map(permission -> modelMapperService.forResponse().map(permission, GetPermissionListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetPermissionResponse getPermissionById(Long id) {
        permissionBusinessRules.checkIfPermissionExists(id);

        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        return modelMapperService.forResponse().map(permission, GetPermissionResponse.class);
    }

    @Override
    public void createPermission(CreatePermissionRequest createPermissionRequest) {
        permissionBusinessRules.checkIfPermissionNameExists(createPermissionRequest.getName());

        Permission permission = modelMapperService.forRequest().map(createPermissionRequest, Permission.class);
        permissionRepository.save(permission);
    }

    @Override
    public void updatePermission(UpdatePermissionRequest updatePermissionRequest) {
        permissionBusinessRules.checkIfPermissionExists(updatePermissionRequest.getId());

        Permission permission = permissionRepository.findById(updatePermissionRequest.getId())
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + updatePermissionRequest.getId()));
        permission.setName(updatePermissionRequest.getName());
        permission.setDescription(updatePermissionRequest.getDescription());
        permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionBusinessRules.checkIfPermissionExists(id);

        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        permissionRepository.delete(permission);
    }
}

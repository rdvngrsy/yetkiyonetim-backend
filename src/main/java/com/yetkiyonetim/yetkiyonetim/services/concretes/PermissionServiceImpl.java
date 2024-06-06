package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import com.yetkiyonetim.yetkiyonetim.repositories.PermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.PermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.CreatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.UpdatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public List<GetPermissionListResponse> getAllPermissions() {
        List<Permission> permissionList = permissionRepository.findAll();
        return permissionList.stream()
                .map(permission -> modelMapperService.forResponse().map(permission, GetPermissionListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetPermissionResponse getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        return modelMapperService.forResponse().map(permission, GetPermissionResponse.class);
    }

    @Override
    public void createPermission(CreatePermissionRequest createPermissionRequest) {
        Permission permission = modelMapperService.forRequest().map(createPermissionRequest, Permission.class);
        permissionRepository.save(permission);
    }

    @Override
    public void updatePermission(UpdatePermissionRequest updatePermissionRequest) {
        Permission permission = permissionRepository.findById(updatePermissionRequest.getId())
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + updatePermissionRequest.getId()));
        permission.setName(updatePermissionRequest.getName());
        permission.setDescription(updatePermissionRequest.getDescription());
        permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        permissionRepository.delete(permission);
    }
}

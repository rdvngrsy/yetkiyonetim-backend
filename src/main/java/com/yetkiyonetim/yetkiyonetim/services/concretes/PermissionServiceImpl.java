package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.businessRules.PermissionBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper.PermissionMapper;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.modelMapper.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import com.yetkiyonetim.yetkiyonetim.repositories.PermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.PermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.CreatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.UpdatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionBusinessRules permissionBusinessRules;
    private final PermissionMapper permissionMapper; // PermissionMapper enjekte edildi

    @Override
    public List<GetPermissionListResponse> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(permission -> permissionMapper.permissionToGetPermissionListResponse(permission))
                .collect(Collectors.toList());
    }


    @Override
    public GetPermissionResponse getPermissionById(Long id) {
        permissionBusinessRules.checkIfPermissionExists(id);

        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        return permissionMapper.permissionToGetPermissionResponse(permission); // MapStruct kullanımı
    }

    @Override
    public void createPermission(CreatePermissionRequest createPermissionRequest) {
        permissionBusinessRules.checkIfPermissionNameExists(createPermissionRequest.getName());

        Permission permission = permissionMapper.createPermissionRequestToPermission(createPermissionRequest); // MapStruct kullanımı
        permissionRepository.save(permission);
    }

    @Override
    public void updatePermission(UpdatePermissionRequest updatePermissionRequest) {
        permissionBusinessRules.checkIfPermissionExists(updatePermissionRequest.getId());

        Permission permission = permissionMapper.updatePermissionRequestToPermission(updatePermissionRequest); // MapStruct kullanımı
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


package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.businessRules.RolePermissionBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.RolePermissionId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.RolePermission;
import com.yetkiyonetim.yetkiyonetim.repositories.RolePermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.RolePermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.CreateRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.DeleteRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionBusinessRules rolePermissionBusinessRules;

    private final RolePermissionRepository rolePermissionRepository;

    private final ModelMapperService modelMapperService;

    @Override
    public List<GetRolePermissionListResponse> getAllRolePermissions() {
        List<RolePermission> rolePermissionList = rolePermissionRepository.findAll();
        return rolePermissionList.stream()
                .map(rolePermission -> modelMapperService.forResponse().map(rolePermission, GetRolePermissionListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetRolePermissionResponse> getPermissionsByRoleId(Long roleId) {
        rolePermissionBusinessRules.checkIfRolePermissionExistsByRoleId(roleId);

        List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(roleId);
        return rolePermissions.stream()
                .map(rolePermission -> modelMapperService.forResponse().map(rolePermission, GetRolePermissionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetRolePermissionResponse> getRolesByPermissionId(Long permissionId) {
        rolePermissionBusinessRules.checkIfRolePermissionExistsByPermissionId(permissionId);

        List<RolePermission> rolePermissions = rolePermissionRepository.findByPermissionId(permissionId);
        return rolePermissions.stream()
                .map(rolePermission -> modelMapperService.forResponse().map(rolePermission, GetRolePermissionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createRolePermission(CreateRolePermissionRequest createRolePermissionRequest) {
        rolePermissionBusinessRules.checkIfRolePermissionExists(createRolePermissionRequest.getRoleId(), createRolePermissionRequest.getPermissionId());
        RolePermission rolePermission = modelMapperService.forRequest().map(createRolePermissionRequest, RolePermission.class);
        rolePermissionRepository.save(rolePermission);
    }

//    @Override
//    public void updateRolePermission(UpdateRolePermissionRequest updateRolePermissionRequest) {
//        RolePermissionId rolePermissionId = new RolePermissionId(updateRolePermissionRequest.getRoleId(), updateRolePermissionRequest.getOldPermissionId());
//        RolePermission rolePermission = rolePermissionRepository.findById(rolePermissionId)
//                .orElseThrow(() -> new RuntimeException("RolePermission not found"));
//        rolePermission.setPermission(new Permission(updateRolePermissionRequest.getNewPermissionId()));
//        rolePermissionRepository.save(rolePermission);
//    }

    @Override
    public void deleteRolePermission(DeleteRolePermissionRequest deleteRolePermissionRequest) {
        rolePermissionBusinessRules.checkForDeleteIfRolePermissionExists(deleteRolePermissionRequest.getRoleId(), deleteRolePermissionRequest.getPermissionId());

        RolePermission rolePermission = rolePermissionRepository.findById(new RolePermissionId(deleteRolePermissionRequest.getRoleId(), deleteRolePermissionRequest.getPermissionId()))
                .orElseThrow(() -> new RuntimeException("RolePermission not found"));
        rolePermissionRepository.delete(rolePermission);
    }

    @Override
    public List<RolePermission> findByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }
}


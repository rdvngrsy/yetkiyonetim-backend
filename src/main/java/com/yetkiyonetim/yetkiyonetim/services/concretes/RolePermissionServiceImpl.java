package com.yetkiyonetim.yetkiyonetim.services.concretes;

import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.RolePermissionId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.RolePermission;
import com.yetkiyonetim.yetkiyonetim.repositories.RolePermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.RolePermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.CreateRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.DeleteRolePermissionRequest;
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
    public List<GetRolePermissionResponse> getPermissionsByRoleId(Long roleId) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(roleId);
        return rolePermissions.stream()
                .map(rolePermission -> modelMapperService.forResponse().map(rolePermission, GetRolePermissionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetRolePermissionResponse> getRolesByPermissionId(Long permissionId) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findByPermissionId(permissionId);
        return rolePermissions.stream()
                .map(rolePermission -> modelMapperService.forResponse().map(rolePermission, GetRolePermissionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createRolePermission(CreateRolePermissionRequest createRolePermissionRequest) {
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
        RolePermission rolePermission = rolePermissionRepository.findById(new RolePermissionId(deleteRolePermissionRequest.getRoleId(), deleteRolePermissionRequest.getPermissionId()))
                .orElseThrow(() -> new RuntimeException("RolePermission not found"));
        rolePermissionRepository.delete(rolePermission);
    }

    @Override
    public List<RolePermission> findByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }
}


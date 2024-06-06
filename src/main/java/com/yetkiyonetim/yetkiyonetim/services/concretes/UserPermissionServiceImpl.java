package com.yetkiyonetim.yetkiyonetim.services.concretes;


import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserPermission;
import com.yetkiyonetim.yetkiyonetim.repositories.UserPermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserPermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userPermission.CreateUserPermissionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Override
    public void save(CreateUserPermissionRequest userPermissionRequest) {
        UserPermission userPermission = new UserPermission();
        userPermission.setUser(userPermissionRequest.getUser());
        userPermission.setPermission(userPermissionRequest.getPermission());
        userPermissionRepository.save(userPermission);
    }
    @Override
    public void deleteByUserIdAndPermissionId(Long userId, Long permissionId) {
        userPermissionRepository.deleteByUserIdAndPermissionId(userId, permissionId);
    }
}

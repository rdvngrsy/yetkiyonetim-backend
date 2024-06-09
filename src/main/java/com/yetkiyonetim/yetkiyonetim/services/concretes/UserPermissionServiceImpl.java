package com.yetkiyonetim.yetkiyonetim.services.concretes;


import com.yetkiyonetim.yetkiyonetim.businessRules.UserPermissionBusinessRules;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserPermission;
import com.yetkiyonetim.yetkiyonetim.repositories.UserPermissionRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserPermissionService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userPermission.CreateUserPermissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPermissionServiceImpl implements UserPermissionService {
    private final UserPermissionBusinessRules userPermissionBusinessRules;

    private final UserPermissionRepository userPermissionRepository;

    @Override
    public void save(CreateUserPermissionRequest userPermissionRequest) {
        userPermissionBusinessRules.checkIfUserPermissionExists(userPermissionRequest.getUser().getId(), userPermissionRequest.getPermission().getId());

        UserPermission userPermission = new UserPermission();
        userPermission.setUser(userPermissionRequest.getUser());
        userPermission.setPermission(userPermissionRequest.getPermission());
        userPermissionRepository.save(userPermission);
    }
    @Override
    public void deleteByUserIdAndPermissionId(Long userId, Long permissionId) {
        userPermissionBusinessRules.checkForDeleteIfUserPermissionExists(userId, permissionId);
        userPermissionRepository.deleteByUserIdAndPermissionId(userId, permissionId);
    }
}

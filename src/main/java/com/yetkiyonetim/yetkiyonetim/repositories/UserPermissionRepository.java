package com.yetkiyonetim.yetkiyonetim.repositories;

import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserPermissionId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, UserPermissionId> {
    void deleteByUserIdAndPermissionId(Long userId, Long permissionId);
    boolean existsByUserIdAndPermissionId(Long userId, Long permissionId);
    boolean existsByUserId(Long userId);
    boolean existsByPermissionId(Long permissionId);
}

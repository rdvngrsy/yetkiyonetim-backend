package com.yetkiyonetim.yetkiyonetim.repositories;

import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserPermissionId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.RolePermission;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, UserPermissionId> {
    List<UserPermission> findByUserId(Long userId);
    List<UserPermission> findByPermissionId(Long permissionId);
    void deleteByUserIdAndPermissionId(Long userId, Long permissionId);
    boolean existsByUserIdAndPermissionId(Long userId, Long permissionId);
    boolean existsByUserId(Long userId);
    boolean existsByPermissionId(Long permissionId);
}

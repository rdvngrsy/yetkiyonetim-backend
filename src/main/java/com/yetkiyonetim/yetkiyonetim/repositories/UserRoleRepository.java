package com.yetkiyonetim.yetkiyonetim.repositories;

import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserRoleId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
}

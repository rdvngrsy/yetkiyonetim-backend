package com.yetkiyonetim.yetkiyonetim.repositories;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}
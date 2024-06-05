package com.yetkiyonetim.yetkiyonetim.entities.concretes;


import com.yetkiyonetim.yetkiyonetim.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role extends BaseEntity  {

    @Column(unique = true, nullable = false)
    private String roleName;

    @Column
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "role")
    private Set<RolePermission> rolePermissions;

//    @Override
//    public String getAuthority() {
//        return name;
//    }
}


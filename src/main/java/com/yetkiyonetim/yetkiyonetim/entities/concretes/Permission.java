package com.yetkiyonetim.yetkiyonetim.entities.concretes;


import com.yetkiyonetim.yetkiyonetim.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
public class Permission extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "permission")
    private Set<RolePermission> rolePermissions;

}


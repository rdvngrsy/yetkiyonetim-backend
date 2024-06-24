package com.yetkiyonetim.yetkiyonetim.entities.concretes;
import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.RolePermissionId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "role_permissions")
@Data
@NoArgsConstructor
@IdClass(RolePermissionId.class)
public class RolePermission implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;


}

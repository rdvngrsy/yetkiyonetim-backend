package com.yetkiyonetim.yetkiyonetim.entities.concretes;


import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserPermissionId;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "user_permissions")
@IdClass(UserPermissionId.class)
public class UserPermission {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

}



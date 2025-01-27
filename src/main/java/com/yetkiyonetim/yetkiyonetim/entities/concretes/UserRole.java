package com.yetkiyonetim.yetkiyonetim.entities.concretes;

import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserRoleId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}

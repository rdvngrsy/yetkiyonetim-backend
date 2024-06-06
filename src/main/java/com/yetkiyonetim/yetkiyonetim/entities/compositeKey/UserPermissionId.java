package com.yetkiyonetim.yetkiyonetim.entities.compositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserPermissionId implements Serializable {
    private Long user;
    private Long permission;
}

package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePermissionRequest {
    private String permissionName;
    private String description;
}
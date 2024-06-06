package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPermissionResponse {
    private Long id;
    private String name;
    private String description;
}
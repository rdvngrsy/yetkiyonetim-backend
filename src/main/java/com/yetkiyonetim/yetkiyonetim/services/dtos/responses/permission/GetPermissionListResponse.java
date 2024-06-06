package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPermissionListResponse {
    private Long id;
    private String name;
    private String description;
}

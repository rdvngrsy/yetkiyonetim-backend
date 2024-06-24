package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetRoleListResponse {
    private Long id;
    private String name;
    private String description;

}

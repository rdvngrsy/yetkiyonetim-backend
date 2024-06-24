package com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.CreatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.UpdatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PermissionMapper {

     PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    // Entity'den DTO'ya dönüşüm
    GetPermissionResponse permissionToGetPermissionResponse(Permission permission);
    GetPermissionListResponse permissionToGetPermissionListResponse(Permission permission);


    // DTO'dan Entity'ye dönüşüm

    Permission createPermissionRequestToPermission(CreatePermissionRequest createPermissionRequest);
    Permission updatePermissionRequestToPermission(UpdatePermissionRequest updatePermissionRequest);

}

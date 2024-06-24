package com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.Role;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.CreateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role.UpdateRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role.GetRoleResponse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    // Entity'den DTO'ya dönüşüm

    GetRoleResponse roleToGetRoleResponse(Role role);
    GetRoleListResponse roleToGetRoleListResponse(Role role);



    // DTO'dan Entity'ye dönüşüm

    Role createRoleRequestToRole(CreateRoleRequest createRoleRequest);
    Role updateRoleRequestToRole(UpdateRoleRequest updateRoleRequest);

}


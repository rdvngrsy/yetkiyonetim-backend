package com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.CreateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.UpdateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserResponse;
import org.mapstruct.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


//    User createUserRequestToUser(CreateUserRequest createUserRequest);
//
//    User updateUserRequestToUser(UpdateUserRequest updateUserRequest);
//
//    User getUserResponseToUser(GetUserResponse getUserResponse);

    GetUserResponse userToGetUserResponse(User user);

    GetUserListResponse userToGetUserListResponse(User user);

}


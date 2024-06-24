package com.yetkiyonetim.yetkiyonetim.services.abstracts;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.CreatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.UpdatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.CreateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.UpdateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserResponse;

import java.util.List;

public interface UserService {
    List<GetUserListResponse> getAllUsers();
    GetUserResponse getUserById(Long id);
    void updateUser(UpdateUserRequest updateUserRequest);
    void createUser(User createUserRequest);
    void deleteUser(Long id);
    GetUserResponse getByUsername(String username);

}
package com.yetkiyonetim.yetkiyonetim.services.concretes;


import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.Role;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import com.yetkiyonetim.yetkiyonetim.repositories.RoleRepository;
import com.yetkiyonetim.yetkiyonetim.repositories.UserRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.CreateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.UpdateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapperService modelMapperService;

    @Override
    public List<GetUserListResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();

        List<GetUserListResponse> userResponse = userList.stream()
                .map(color ->this.modelMapperService.forResponse()
                        .map(color, GetUserListResponse.class)).collect(Collectors.toList());
        return userResponse;
    }

    @Override
    public GetUserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return this.modelMapperService.forResponse().map(user, GetUserResponse.class);
    }

    @Override
    public void createUser(User createUserRequest) {
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(updateUserRequest.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + updateUserRequest.getId()));
        user.setPassword(updateUserRequest.getPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found" + username));
    }

}

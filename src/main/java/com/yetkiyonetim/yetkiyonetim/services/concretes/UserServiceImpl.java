package com.yetkiyonetim.yetkiyonetim.services.concretes;


import com.yetkiyonetim.yetkiyonetim.businessRules.UserBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.mapStructMapper.UserMapper;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.modelMapper.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import com.yetkiyonetim.yetkiyonetim.repositories.UserRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.UpdateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserBusinessRules userBusinessRules;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ModelMapperService modelMapperService;


    @Override
    public List<GetUserListResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> userMapper.userToGetUserListResponse(user))
                .collect(Collectors.toList());
    }

    @Override
    public GetUserResponse getUserById(Long id) {
        userBusinessRules.checkIfUserExists(id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.userToGetUserResponse(user);
    }


    @Override
    public void createUser(User createUserRequest) {
        userBusinessRules.checkIfUsernameExists(createUserRequest.getUsername());
//        User user = userMapper.createUserRequestToUser(createUserRequest);
        userRepository.save(createUserRequest);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        userBusinessRules.checkIfUserExists(updateUserRequest.getId());

        User user = userRepository.findById(updateUserRequest.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + updateUserRequest.getId()));
        user.setPassword(updateUserRequest.getPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userBusinessRules.checkIfUserExists(id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }


    @Override
    public GetUserResponse getByUsername(String username) {
        userBusinessRules.checkIfUsernameExists(username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return userMapper.userToGetUserResponse(user);
    }
}

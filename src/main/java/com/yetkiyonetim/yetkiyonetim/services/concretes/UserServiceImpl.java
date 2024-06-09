package com.yetkiyonetim.yetkiyonetim.services.concretes;


import com.yetkiyonetim.yetkiyonetim.businessRules.UserBusinessRules;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import com.yetkiyonetim.yetkiyonetim.repositories.UserRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.UpdateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserBusinessRules userBusinessRules;
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;

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
        userBusinessRules.checkIfUserExists(id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return this.modelMapperService.forResponse().map(user, GetUserResponse.class);
    }

    @Override
    public void createUser(User createUserRequest) {
        userBusinessRules.checkIfUsernameExists(createUserRequest.getUsername());
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        userRepository.save(user);
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
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found" + username));
    }

}

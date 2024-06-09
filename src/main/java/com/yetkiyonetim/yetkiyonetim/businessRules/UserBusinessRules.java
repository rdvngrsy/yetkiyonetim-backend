package com.yetkiyonetim.yetkiyonetim.businessRules;

import com.yetkiyonetim.yetkiyonetim.core.utilities.exceptions.BusinessException;
import com.yetkiyonetim.yetkiyonetim.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void checkIfUsernameExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException("Username already exists.");
        }
    }

    public void checkIfUserExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new BusinessException("User not found.");
        }
    }
}

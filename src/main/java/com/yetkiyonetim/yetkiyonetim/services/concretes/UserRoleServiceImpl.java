package com.yetkiyonetim.yetkiyonetim.services.concretes;
import com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.ModelMapperService;
import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.UserRoleId;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserRole;
import com.yetkiyonetim.yetkiyonetim.repositories.UserRoleRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserRoleService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.CreateUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.DeleteUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole.UpdateUserRoleRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole.GetUserRoleListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole.GetUserRoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public List<GetUserRoleListResponse> getAllUserRoles() {
        List<UserRole> userRoleList = userRoleRepository.findAll();
        return userRoleList.stream()
                .map(userRole -> modelMapperService.forResponse().map(userRole, GetUserRoleListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetUserRoleResponse> getRolesByUserId(Long userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        return userRoles.stream()
                .map(userRole -> modelMapperService.forResponse().map(userRole, GetUserRoleResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetUserRoleResponse> getUsersByRoleId(Long roleId) {
        List<UserRole> userRoles = userRoleRepository.findByRoleId(roleId);
        return userRoles.stream()
                .map(userRole -> modelMapperService.forResponse().map(userRole, GetUserRoleResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createUserRole(CreateUserRoleRequest createUserRoleRequest) {
        UserRole userRole = modelMapperService.forRequest().map(createUserRoleRequest, UserRole.class);
        userRoleRepository.save(userRole);
    }

//    @Override
//    public void updateUserRole(UpdateUserRoleRequest updateUserRoleRequest) {
//        UserRoleId id = new UserRoleId(updateUserRoleRequest.getUserId(), updateUserRoleRequest.getRoleId());
//        UserRole userRole = userRoleRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("UserRole not found with id: " + id));
//        // Update fields as necessary
//        userRoleRepository.save(userRole);
//    }

    @Override
    public void deleteUserRole(DeleteUserRoleRequest deleteUserRoleRequest) {
        UserRole userRole = userRoleRepository.findById(new UserRoleId(deleteUserRoleRequest.getUserId(), deleteUserRoleRequest.getRoleId()))
                .orElseThrow(() -> new RuntimeException("UserRole not found"));
        userRoleRepository.delete(userRole);
    }

}


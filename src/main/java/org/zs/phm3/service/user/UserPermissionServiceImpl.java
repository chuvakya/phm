package org.zs.phm3.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.user.UserPermission;
import org.zs.phm3.repository.user.UserPermissionRepository;

import java.util.List;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Override
    public List<UserPermission> getAll() {
        return userPermissionRepository.getAll();
    }
}

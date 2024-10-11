package org.zs.phm3.service.user;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.user.UserRole;
import org.zs.phm3.repository.user.UserRoleRepository;
import org.zs.phm3.repository.user.UserRoleUserPermissionGroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRoleUserPermissionGroupRepository userRoleUserPermissionGroupRepository;

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Transactional
    @Override
    public void saveGroups(Integer userRoleId, List<Integer> groupIds) {
        if (!groupIds.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("INSERT INTO user_role_user_permission_group " +
                    "(user_permission_group_id, user_role_id) VALUES (" + groupIds.get(0) + ", " + userRoleId + ");");
            for (int i = 1; i < groupIds.size(); i++) {
                stringBuilder.append("INSERT INTO user_role_user_permission_group " +
                        "(user_permission_group_id, user_role_id) VALUES (" + groupIds.get(i) + ", " + userRoleId + ");");
            }
            entityManager.createNativeQuery(stringBuilder.toString()).executeUpdate();
        }
    }

    @Override
    public Integer getCountByName(String name) {
        return userRoleRepository.getCountByName(name);
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleRepository.getAll();
    }

    @Override
    public String getByIdJSON(Integer roleId) {
        UserRole userRole = userRoleRepository.findById(roleId).get();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userRole.getId());
        jsonObject.put("name", userRole.getName());
        jsonObject.put("groupIds", userRoleUserPermissionGroupRepository.getPermissionGroupIdsByUserRoleId(
                userRole.getId()));
        return jsonObject.toJSONString();
    }

    @Override
    public UserRole getById(Integer roleId) {
        return userRoleRepository.findById(roleId).get();
    }

    @Override
    public void addGroup(Integer roleId, Integer groupId) {
        userRoleUserPermissionGroupRepository.addGroup(roleId, groupId);
    }

    @Override
    public Integer getCountByRoleIdAndGroupId(Integer roleId, Integer groupId) {
        return userRoleUserPermissionGroupRepository.getCountByRoleIdAndGroupId(roleId, groupId);
    }

    @Override
    public void deleteGroup(Integer roleId, Integer groupId) {
        userRoleUserPermissionGroupRepository.deleteByRoleIdAndGroupId(roleId, groupId);
    }
}

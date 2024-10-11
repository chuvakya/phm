package org.zs.phm3.service.user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.user.UserPermission;
import org.zs.phm3.models.user.UserPermissionGroup;
import org.zs.phm3.repository.user.UserGroupPermissionRepository;
import org.zs.phm3.repository.user.UserPermissionGroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserPermissionGroupServiceImpl implements UserPermissionGroupService {

    @Autowired
    private UserPermissionGroupRepository userPermissionGroupRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserGroupPermissionRepository userGroupPermissionRepository;

    @Transactional
    @Override
    public void savePermissions(Integer userPermissionGroupId, List<Integer> permissionIds) {
        if (!permissionIds.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("INSERT INTO user_group_permission " +
                    "(user_permission_id, user_permission_group_id) VALUES (" + permissionIds.get(0) + ", " +
                    userPermissionGroupId + ");");
            for (int i = 1; i < permissionIds.size(); i++) {
                stringBuilder.append("INSERT INTO user_group_permission " +
                        "(user_permission_id, user_permission_group_id) VALUES (" + permissionIds.get(i) + ", " +
                        userPermissionGroupId + ");");
            }
            entityManager.createNativeQuery(stringBuilder.toString()).executeUpdate();
        }
    }

    @Override
    public UserPermissionGroup save(UserPermissionGroup userPermissionGroup) {
        return userPermissionGroupRepository.save(userPermissionGroup);
    }

    @Override
    public Integer getCountByName(String name) {
        return userPermissionGroupRepository.getCountByName(name);
    }

    @Override
    public String getList() {
        List<UserPermissionGroup> userPermissionGroups = userPermissionGroupRepository.getAll();
        List<Integer> permissionsIds = new ArrayList<>();
        for (UserPermissionGroup userPermissionGroup : userPermissionGroups) {
            permissionsIds.add(userPermissionGroup.getId());
        }
        List<List<Object>> permissions = userGroupPermissionRepository.getAllByUserPermissionGroupIds(permissionsIds);
        JSONArray jsonArray = new JSONArray();
        for (UserPermissionGroup userPermissionGroup : userPermissionGroups) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", userPermissionGroup.getId());
            jsonObject.put("name", userPermissionGroup.getName());
            List<String> descriptions = new ArrayList<>();
            for (List<Object> permission : permissions) {
                if (permission.get(0) == userPermissionGroup.getId()) {
                    descriptions.add((String) permission.get(1));
                }
            }
            StringBuilder stringBuilder = new StringBuilder("");
            if (!descriptions.isEmpty()) {
                stringBuilder.append(descriptions.get(0));
                for (int i = 1; i < descriptions.size(); i++) {
                    stringBuilder.append(". " + descriptions.get(i));
                }
                stringBuilder.append(".");
            }
            jsonObject.put("description", stringBuilder.toString());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String getByIdJSON(Integer permissionGroupId) {
        UserPermissionGroup userPermissionGroup = userPermissionGroupRepository.findById(permissionGroupId).get();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userPermissionGroup.getId());
        jsonObject.put("name", userPermissionGroup.getName());
        List<List<Object>> permissions = userGroupPermissionRepository.getAllByUserPermissionGroupIds(
                Arrays.asList(permissionGroupId));
        JSONArray jsonArray = new JSONArray();
        for (List<Object> permission : permissions) {
            jsonArray.add(permission.get(2));
        }
        jsonObject.put("permissionIds", jsonArray);
        return jsonObject.toJSONString();
    }

    @Override
    public UserPermissionGroup getById(Integer permissionGroupId) {
        return userPermissionGroupRepository.findById(permissionGroupId).get();
    }

    @Override
    public Integer getCountByGroupIdAndPermissionId(Integer groupId, Integer permissionId) {
        return userGroupPermissionRepository.getCountByGroupIdAndPermissionId(groupId, permissionId);
    }

    @Override
    public void addPermission(Integer groupId, Integer permissionId) {
        userGroupPermissionRepository.addPermission(groupId, permissionId);
    }

    @Override
    public void deletePermission(Integer groupId, Integer permissionId) {
        userGroupPermissionRepository.deleteByUserPermissionGroup_IdAnAndUserPermission_Id(groupId, permissionId);
    }
}

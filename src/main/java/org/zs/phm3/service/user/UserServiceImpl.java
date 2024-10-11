package org.zs.phm3.service.user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.models.user.UserRole;
import org.zs.phm3.repository.user.UserEntityUserRoleRepository;
import org.zs.phm3.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserEntityUserRoleRepository userEntityUserRoleRepository;

    @Value(value = "${user-picture-folder}")
    private String pictureFolderPath;

    @Override
    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Transactional
    @Override
    public void saveUserRoles(Long userEntityId, List<Integer> roleIds) {
        if (!roleIds.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("INSERT INTO user_entity_user_role " +
                    "(user_entity_id, user_role_id) VALUES (" + userEntityId + ", " + roleIds.get(0) + ");");
            for (int i = 1; i < roleIds.size(); i++) {
                stringBuilder.append("INSERT INTO user_entity_user_role " +
                        "(user_entity_id, user_role_id) VALUES (" + userEntityId + ", " + roleIds.get(i) + ");");
            }
            System.out.println(stringBuilder.toString());
            entityManager.createNativeQuery(stringBuilder.toString()).executeUpdate();
        }
    }

    @Override
    public String getList() {
        List<UserEntity> userEntities = userRepository.getAll();
        JSONArray jsonArray = new JSONArray();
        for (UserEntity userEntity : userEntities) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", userEntity.getId());
            jsonObject.put("name", userEntity.getName());
            jsonObject.put("email", userEntity.getEmail());
            jsonObject.put("picturePath", "/api/user/getPictureByUserId/" + userEntity.getId());
            jsonObject.put("lastSeen", userEntity.getLastSeen());
            if (userEntity.getAccess()) {
                jsonObject.put("access", "Access granted");
            } else {
                jsonObject.put("access", "Access revoked");
            }
            List<List<Object>> roles = userEntityUserRoleRepository.getAllUserEntityIdAndRoleName();
            jsonObject.put("roles", getRoles(userEntity.getId(), roles));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String getJSONByUserEntityId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userEntity.getId());
        jsonObject.put("firstName", userEntity.getFirstName());
        jsonObject.put("lastName", userEntity.getLastName());
        jsonObject.put("email", userEntity.getEmail());
        jsonObject.put("organization", userEntity.getOrganization());
        jsonObject.put("login", userEntity.getLogin());
        jsonObject.put("picturePath", "/api/user/getPictureByUserId/" + userEntity.getId());
        List<UserRole> userRoles = userEntityUserRoleRepository.getAllUserRoleByUserEntityId(userId);
        JSONArray jsonArray = new JSONArray();
        StringBuilder stringBuilder = new StringBuilder("");
        for (UserRole userRole : userRoles) {
            JSONObject object = new JSONObject();
            object.put("roleId", userRole.getId());
            object.put("roleName", userRole.getName());
            jsonArray.add(object);
        }
        if (!userRoles.isEmpty()) {
            stringBuilder.append(userRoles.get(0).getName());
        }
        for (int i = 1; i < userRoles.size(); i++) {
            stringBuilder.append(", " + userRoles.get(i).getName());
        }
        jsonObject.put("roles", jsonArray);
        jsonObject.put("roleNames", stringBuilder.toString());
        return jsonObject.toJSONString();
    }

    @Override
    public void setRole(Long userId, Integer roleId) {
        userRepository.addUserEntityIdAndUserRoleId(userId, roleId);
    }

    @Override
    public void deleteRoleByUserIdAndRoleId(Long userEntityId, Integer userRoleId) {
        userRepository.deleteRoleByUserIdAndRoleId(userEntityId, userRoleId);
    }

    @Override
    public Integer getCountByUserIdAndRoleId(Long userId, Integer roleId) {
        return userEntityUserRoleRepository.getCountByUserIdAndRoleId(userId, roleId);
    }

    public UserEntity getOneById(Long id) throws PhmException {
        return userRepository.findById(id).orElseThrow(() ->
                new PhmException("No User found with id=" + id, PhmErrorCode.AUTHENTICATION));
    }

    @Override
    public UserEntity getById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public String getAllUsers() {
        List<UserEntity> users = userRepository.getAll();
        JSONArray jsonArray = new JSONArray();
        for (UserEntity user : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("name", user.getName());
            List<List<Object>> roles = userEntityUserRoleRepository.getAllUserEntityIdAndRoleName();
            jsonObject.put("group", getRoles(user.getId(), roles));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    private String getRoles(Long userEntityId, List<List<Object>> roles) {
        StringBuilder stringBuilder = new StringBuilder("");

        List<String> roleNames = new ArrayList<>();
        for (List<Object> role : roles) {
            if (((BigInteger) role.get(0)).longValue() == userEntityId) {
                roleNames.add((String) role.get(1));
            }
        }
        if (!roleNames.isEmpty()) {
            stringBuilder.append(roleNames.get(0));
            for (int i = 1; i < roleNames.size(); i++) {
                stringBuilder.append(", " + roleNames.get(i));
            }
        }
        return stringBuilder.toString();
    }

    public UserEntity getActualUser() throws PhmException {
        return userRepository.findById(1L).orElseThrow(() ->
                new PhmException("No Actual User found", PhmErrorCode.AUTHENTICATION));
    }
}

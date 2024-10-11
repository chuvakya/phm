package org.zs.phm3.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.service.user.UserRoleService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SQLHelper sqlHelper;

    @Value(value = "${user-picture-folder}")
    private String pictureFolderPath;

    @GetMapping(value = "getAllUsers", produces = "application/json")
    public String getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String login,
                         @RequestParam String email, @RequestParam String organization, @RequestParam String password,
                         @RequestParam(required = false) Integer roleId,
                         @RequestPart(required = false) MultipartFile pictureFile) {
        if (userService.getByLogin(login) != null) {
            return "This login already exist!";
        }
        System.out.println(pictureFile.getOriginalFilename());
        String [] s = pictureFile.getOriginalFilename().split("\\.");

        UserEntity userEntity = new UserEntity(login, firstName + " " + lastName, firstName, lastName,
                null, true, pictureFile != null ? s[s.length - 1] : null, password, email,
                organization, false);
        userService.save(userEntity);
        userService.saveUserRoles(userEntity.getId(), Arrays.asList(roleId));

        if (pictureFile != null) {
            File file = new File(pictureFolderPath + userEntity.getId() + "." + s[s.length - 1]);
            try {
                pictureFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Success!";
    }

    @GetMapping(value = "getList", produces = "application/json")
    public String getList() {
        return userService.getList();
    }

    @DeleteMapping(value = "deleteById/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        userEntity.setDeleted(true);
        userEntity.setName("Deleted");
        userService.save(userEntity);
    }

    @GetMapping(value = "getById/{userId}", produces = "application/json")
    public String getById(@PathVariable Long userId) {
        return userService.getJSONByUserEntityId(userId);
    }

    @PostMapping(value = "updateAccess/{userId}/{isAccess}")
    public void updateAccess(@PathVariable Boolean isAccess, @PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        userEntity.setAccess(isAccess);
        userService.save(userEntity);
    }

    @PatchMapping(value = "addRole/{userId}/{roleId}")
    public String addRole(@PathVariable Long userId, @PathVariable Integer roleId) {
        if (userService.getCountByUserIdAndRoleId(userId, roleId) > 0) {
            return "Error! This record already exist!";
        }
        userService.setRole(userId, roleId);
        return "Success!";
    }

    @PatchMapping(value = "selectRole/{userId}/{roleId}")
    public String selectRole(@PathVariable Long userId, @PathVariable Integer roleId) {
        if (userService.getCountByUserIdAndRoleId(userId, roleId) > 0) {
            return "Error! This record already exist!";
        }
        sqlHelper.deleteAll("user_entity_user_role", "user_entity_id", Arrays.asList(userId));
        userService.setRole(userId, roleId);
        return "Success!";
    }

    @PatchMapping(value = "deleteRole/{userId}/{roleId}")
    public void deleteRoleByUser(@PathVariable Long userId, @PathVariable Integer roleId) {
        userService.deleteRoleByUserIdAndRoleId(userId, roleId);
    }

    @PatchMapping(value = "updateLogin/{userId}")
    public String updateLogin(@RequestParam String login, @PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        if (userService.getByLogin(login) != null && !userEntity.getLogin().equals(login)) {
            return "This login already exist!";
        } else if (!userEntity.getLogin().equals(login)) {
            userEntity.setLogin(login);
            userService.save(userEntity);
        }
        return "Success!";
    }

    @PatchMapping(value = "updatePassword/{userId}")
    public void updatePassword(@RequestParam String password, @PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        userEntity.setPassword(password);
        userService.save(userEntity);
    }

    @PatchMapping(value = "updateFirstName/{userId}")
    public void updateLFirstName(@RequestParam String firstName, @PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        userEntity.setFirstName(firstName);
        userEntity.setName(firstName + " " + userEntity.getLastName());
        userService.save(userEntity);
    }

    @PatchMapping(value = "updateLastName/{userId}")
    public void updateLastName(@RequestParam String lastName, @PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        userEntity.setLastName(lastName);
        userEntity.setName(userEntity.getFirstName() + " " + lastName);
        userService.save(userEntity);
    }

    @PatchMapping(value = "updateEmail/{userId}")
    public void updateEmail(@RequestParam String email, @PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        userEntity.setEmail(email);
        userService.save(userEntity);
    }

    @PatchMapping(value = "updateOrganization/{userId}")
    public void updateOrganization(@RequestParam String organization, @PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        userEntity.setOrganization(organization);
        userService.save(userEntity);
    }

    @PatchMapping(value = "updatePicture/{userId}")
    public void updatePicture(@RequestParam MultipartFile pictureFile, @PathVariable Long userId) {
        UserEntity userEntity = userService.getById(userId);
        String [] s = pictureFile.getOriginalFilename().split("\\.");
        userEntity.setPictureExtention(s[s.length - 1]);
        if (pictureFile != null) {
            File file = new File(pictureFolderPath + userEntity.getId() + "." + s[s.length - 1]);
            try {
                pictureFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userService.save(userEntity);
    }

    @GetMapping(value = "getPictureByUserId/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPicture(@PathVariable Long userId) throws IOException {
        UserEntity userEntity = userService.getById(userId);
        InputStream is = null;
        try {
            is = new FileInputStream(
                    new File(pictureFolderPath + userId + "." + userEntity.getPictureExtention()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is.readAllBytes();
    }

}

package com.jwt.jwt.controller;

import com.jwt.jwt.entity.Role;
import com.jwt.jwt.entity.User;
import com.jwt.jwt.model.DTO.UserDTO;
import com.jwt.jwt.model.request.InsertRequest;
import com.jwt.jwt.model.request.UpdateRequest;
import com.jwt.jwt.service.RoleService;
import com.jwt.jwt.service.UserService;
import com.jwt.jwt.service.ValidationInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @GetMapping("/get-users")
    public ResponseEntity<Object> homePage(@RequestParam(name = "key", required = false, defaultValue = "") String key) {
        List<User> listUser;
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        if (key.equals("")) {
            listUser =  userService.getAllUser();
        } else {
            listUser = userService.getAllUserByKey(key);
        }
        if (listUser == null) {
            response.put("responseCode", "03");
            response.put("message", "Empty.");
        } else {
            List<UserDTO> listUserDTO = new ArrayList<>();
            for (User user : listUser) {
                listUserDTO.add(new UserDTO().toUserDTO(user));
            }
            data.put("listUser", listUserDTO);
            response.put("message", "Success.");
            response.put("responseCode", "00");
        }
        response.put("data", data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long userId) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        if (userService.deleteUser(userId)) {
            response.put("message", "Delete success.");
            response.put("responseCode", "00");
        } else {
            response.put("message", "User does not exist fail.");
            response.put("responseCode", "05");
        }
        response.put("data",data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Object> updatePage(@PathVariable("userId") Long id){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        User user = userService.getUserById(id);
        if (user != null) {
            data.put("user",new UserDTO().toUserDTO(user));
            response.put("message", "Success.");
            response.put("responseCode", "00");
        } else {
            response.put("message", "Not exist.");
            response.put("responseCode", "05");
        }
        response.put("data",data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-roles")
    public ResponseEntity<Object> insertPage(){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        List<Role> listRole = roleService.getAllRole();
        if (listRole == null) {
            response.put("responseCode", "03");
            response.put("message", "Empty.");
        } else {
            response.put("responseCode", "00");
            response.put("message", "Success.");
        }
        data.put("listRole", listRole);
        response.put("data",data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/insert-user")
    public ResponseEntity<Object> insertUser(@RequestBody InsertRequest insertRequest){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        ValidationInput validate = new ValidationInput();
        if (insertRequest.getUsername() != null && insertRequest.getPassword() != null && insertRequest.getAddress() != null && insertRequest.getRoleId() != null && !insertRequest.getUsername().equals("") && !insertRequest.getPassword().equals("") && !insertRequest.getAddress().equals("")) {
            if (validate.validatePassword(insertRequest.getPassword()) && !validate.validateUsername(insertRequest.getUsername()) && insertRequest.getUsername().length() > 8 && insertRequest.getPassword().length() > 8) {
                if (roleService.getRoleById(insertRequest.getRoleId()) != null) {
                    int result = userService.insertUser(insertRequest);
                    if (result == 1) {
                        response.put("message", "Insert success.");
                        response.put("responseCode", "00");
                    } else if (result == -1) {
                        response.put("message", "User exists.");
                        response.put("responseCode", "04");
                    } else {
                        response.put("message", "User fail.");
                        response.put("responseCode", "99");
                    }
                } else {
                    response.put("message", "Role does not exist.");
                    response.put("responseCode", "09");
                }
            } else {
                response.put("message", "Username or password invalid.");
                response.put("responseCode", "01");
            }
        } else {
            response.put("message", "Input empty.");
            response.put("responseCode", "02");
        }
        response.put("data",data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-user")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateRequest updateRequest){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        ValidationInput validate = new ValidationInput();
        if (updateRequest.getUsername() != null && updateRequest.getAddress() != null && updateRequest.getRole() != null && !updateRequest.getUsername().equals("")  && !updateRequest.getAddress().equals("")) {
            if (!validate.validateUsername(updateRequest.getUsername()) && updateRequest.getUsername().length() > 8) {
                if (roleService.getRoleById(updateRequest.getRole()) != null) {
                    int result = userService.updateUser(updateRequest);
                    if (result == 1) {
                        response.put("message", "Update success.");
                        response.put("responseCode", "00");
                    } else if (result == 0) {
                        response.put("message", "Update fail.");
                        response.put("responseCode", "99");
                    } else {
                        response.put("message", "User does not exist.");
                        response.put("responseCode", "05");
                    }
                } else {
                    response.put("message", "Role does not exist.");
                    response.put("responseCode", "09");
                }
            } else {
                response.put("message", "Username invalid.");
                response.put("responseCode", "01");
            }
        }  else {
            response.put("message", "Input empty.");
            response.put("responseCode", "02");
        }
            response.put("data",data);
            return ResponseEntity.ok(response);
        }
}

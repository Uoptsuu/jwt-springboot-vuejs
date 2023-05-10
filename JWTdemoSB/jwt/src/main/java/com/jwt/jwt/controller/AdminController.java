package com.jwt.jwt.controller;

import com.jwt.jwt.entity.User;
import com.jwt.jwt.model.DTO.UserDTO;
import com.jwt.jwt.model.request.InsertRequest;
import com.jwt.jwt.model.request.UpdateRequest;
import com.jwt.jwt.service.RoleService;
import com.jwt.jwt.service.UserService;
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
    @GetMapping("/home")
    public ResponseEntity<Object> homePage(@RequestParam(name = "key", required = false, defaultValue = "") String key) {
        List<User> userList = new ArrayList<>();
        if (key.equals("")) {
            userList =  userService.getAllUser();
        } else {
            userList = userService.getAllUserByKey(key);
        }
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO().toUserDTO(user));
        }
        return ResponseEntity.ok(userDTOList);
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("user_id") Long userId) {
        HashMap<String, String> map = new HashMap<>();
        if (userService.deleteUser(userId)) {
            map.put("message", "Delete success.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "Delete fail.");
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(map);
        }
    }

    @GetMapping("/update/{user_id}")
    public ResponseEntity<Object> updatePage(@PathVariable("user_id") Long id){
        HashMap<String, Object> map = new HashMap<>();
        User user = userService.getUserById(id);
        if (user != null) {
            map.put("user", new UserDTO().toUserDTO(user));
            map.put("listRole", roleService.getAllRole());
            return ResponseEntity.ok(map);
        } else {
            map.put("user", null);
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(map);
        }
    }

    @GetMapping("/insert")
    public ResponseEntity<Object> insertPage(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("listRole", roleService.getAllRole());
        return ResponseEntity.ok(map);
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insertUser(@RequestBody InsertRequest insertRequest){
        HashMap<String, String> map = new HashMap<>();
        if (userService.insertUser(insertRequest)) {
            map.put("message", "Insert success.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "Insert fail.");
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(map);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateRequest updateRequest){
        HashMap<String, String> map = new HashMap<>();
        if (userService.updateUser(updateRequest)) {
            map.put("message", "Update success.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "Update fail.");
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(map);
        }
    }
}

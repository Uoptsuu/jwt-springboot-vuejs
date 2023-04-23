package com.jwt.jwt.controller;

import com.jwt.jwt.entity.User;
import com.jwt.jwt.model.DTO.UserDTO;
import com.jwt.jwt.model.request.InsertRequest;
import com.jwt.jwt.model.request.UpdateRequest;
import com.jwt.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    @GetMapping("/home")
    public ResponseEntity<List> homePage(){
        List<User> userList = userService.getAllUser();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO().toUserDTO(user));
        }
        return ResponseEntity.ok(userDTOList);
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Map> deleteUser(@PathVariable("user_id") Long userId){
        HashMap<String, String> map = new HashMap<>();
        if (userService.deleteUser(userId)) {
            map.put("message", "Delete success.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "Delete fail.");
            return ResponseEntity.badRequest().body(map);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<Map> insertUser(@RequestBody InsertRequest insertRequest){
        HashMap<String, String> map = new HashMap<>();
        if (userService.insertUser(insertRequest)) {
            map.put("message", "Insert success.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "Insert fail.");
            return ResponseEntity.badRequest().body(map);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateUser(@RequestBody UpdateRequest updateRequest){
        HashMap<String, String> map = new HashMap<>();
        if (userService.updateUser(updateRequest)) {
            map.put("message", "Update success.");
            return ResponseEntity.ok(map);
        } else {
            map.put("message", "Update fail.");
            return ResponseEntity.badRequest().body(map);
        }
    }
}
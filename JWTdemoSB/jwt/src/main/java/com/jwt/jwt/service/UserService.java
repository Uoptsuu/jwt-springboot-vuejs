package com.jwt.jwt.service;

import com.jwt.jwt.entity.Role;
import com.jwt.jwt.entity.User;
import com.jwt.jwt.model.request.InsertRequest;
import com.jwt.jwt.model.request.UpdateRequest;
import com.jwt.jwt.repository.RoleRepository;
import com.jwt.jwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService{

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    public List<User> getAllUserByKey(Long key) {
        return userRepository.findAllByKey(key);
    }
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void setRole(String userName, String roleName) {
        User user = userRepository.findByUsername(userName).orElseThrow();
        Role role = roleRepository.findByName(roleName);
        user.setRole(role);
    }
    public boolean deleteUser(Long userId) {
        if (userRepository.countById(userId) > 0) {
            userRepository.deleteSoft(userId);
            return true;
        } else return false;
    }

    public boolean insertUser(InsertRequest insertRequest) {
        if (userRepository.countByUsername(insertRequest.getUsername()) > 0) {
            return false;
        } else {
            if (saveUser(new User(null, insertRequest.getUsername(), insertRequest.getPassword(), insertRequest.getAddress())) != null) {
                setRole(insertRequest.getUsername(), roleRepository.findById(insertRequest.getRoleId()).orElseThrow().getName());
                return true;
            } else return false;
        }
    }

    public boolean updateUser(UpdateRequest updateRequest) {
        if (userRepository.findById(updateRequest.getId()).isPresent()) {
            User user = userRepository.findById(updateRequest.getId()).get();
            //user.setActive(updateRequest.getIsActive() != 0);
            user.setActive(updateRequest.isActive());
            //user.setPassword(updateRequest.getPassword());
            user.setAddress(updateRequest.getAddress());
            user.setUsername(updateRequest.getUsername());
            User userSave = saveUser(user);
            if (userSave != null) {
                setRole(updateRequest.getUsername(), roleRepository.findById(updateRequest.getRole()).orElseThrow().getName());
                return true;
            } else return false;
        } else return false;
    }

    public User getUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else return null;
    }

}

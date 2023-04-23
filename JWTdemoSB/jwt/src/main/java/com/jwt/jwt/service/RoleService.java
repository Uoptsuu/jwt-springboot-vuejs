package com.jwt.jwt.service;

import com.jwt.jwt.entity.Role;
import com.jwt.jwt.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
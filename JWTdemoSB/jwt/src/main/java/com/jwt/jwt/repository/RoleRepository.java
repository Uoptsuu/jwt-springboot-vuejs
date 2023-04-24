package com.jwt.jwt.repository;

import com.jwt.jwt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Optional<Role> findById(Long id);
    Role save(Role role);
    List<Role> findAll();

    @Query(value = "SELECT role_name FROM users JOIN roles ON users.role_id = roles.role_id WHERE username = :username AND is_active = TRUE AND is_delete = FALSE", nativeQuery = true)
    Role findRoleByUsername(@Param("username") String username);
}

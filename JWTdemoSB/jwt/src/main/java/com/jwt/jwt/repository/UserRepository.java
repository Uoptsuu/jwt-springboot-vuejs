package com.jwt.jwt.repository;

import com.jwt.jwt.entity.Role;
import com.jwt.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE is_delete = false", nativeQuery = true)
    List<User> findAll();
    @Query(value = "SELECT * FROM users WHERE is_delete = false AND username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);
    User save(User user);

    @Query(value = "SELECT role_name FROM users JOIN roles ON users.role_id = roles.role_id WHERE username = :username AND is_active = TRUE AND is_delete = FALSE", nativeQuery = true)
    Role findRoleByUser(@Param("username") String username);

    @Query(value = "SELECT count(*) FROM users WHERE is_delete = FALSE AND user_id = :user_id", nativeQuery = true)
    Integer countById(@Param("user_id") Long userId);

    @Query(value = "SELECT count(*) FROM users WHERE is_delete = FALSE AND username = :username", nativeQuery = true)
    Integer countByUsername(@Param("username") String username);

    @Modifying
    @Query(value = "UPDATE users SET is_delete = TRUE, is_active = false WHERE user_id = :user_id", nativeQuery = true)
    void deleteSoft(@Param("user_id") Long userId);
    @Query(value = "SELECT u FROM User u WHERE u.isDelete = false and concat(u.username,u.address) like %?1%")
    List<User> findAllByKey(String key);

    //void deleteById(Long id);
}

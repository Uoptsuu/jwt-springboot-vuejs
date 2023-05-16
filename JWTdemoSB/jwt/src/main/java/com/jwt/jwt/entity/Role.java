package com.jwt.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

//    @Column(name = "is_active")
//    private boolean isActive;
//    @Column(name = "is_delete")
//    private boolean isDelete;
//    @Column(name = "create_by")
//    private Long createBy;
//    @Column(name = "create_at")
//    private LocalDateTime createAt;
//    @Column(name = "update_by")
//    private Long updateBy;
//    @Column(name = "update_at")
//    private LocalDateTime updateAt;
//    @Column(name = "delete_by")
//    private Long deleteBy;
//    @Column(name = "delete_at")
//    private LocalDateTime deleteAt;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> user = new HashSet<>();

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }
}

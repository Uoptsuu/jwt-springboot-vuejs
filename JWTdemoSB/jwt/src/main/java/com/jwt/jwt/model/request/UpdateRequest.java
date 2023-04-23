package com.jwt.jwt.model.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRequest {
    private Long id;
    private String username;
    private String password;
    private String address;
    private int isActive;
    //private boolean isActive;
    private Long roleId;
}

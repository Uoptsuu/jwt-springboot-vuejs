package com.jwt.jwt.model.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InsertRequest {
    private String username;
    private String password;
    private String address;
    private Long roleId;
}

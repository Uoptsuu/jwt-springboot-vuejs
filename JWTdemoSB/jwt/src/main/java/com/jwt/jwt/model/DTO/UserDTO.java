package com.jwt.jwt.model.DTO;

import com.jwt.jwt.entity.User;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private boolean isActive;
    private String address;
    private Long role;

    public UserDTO toUserDTO(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.isActive(), user.getAddress(), user.getRole().getId());
    }
}

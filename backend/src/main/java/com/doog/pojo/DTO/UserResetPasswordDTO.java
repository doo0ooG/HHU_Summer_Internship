package com.doog.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResetPasswordDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
}

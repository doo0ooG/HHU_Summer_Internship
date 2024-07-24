package com.doog.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPermissionDTO {
    private String username;
    private Integer per;
    private Integer clubId;
}

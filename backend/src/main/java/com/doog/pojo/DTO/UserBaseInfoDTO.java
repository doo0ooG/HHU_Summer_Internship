package com.doog.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseInfoDTO {
    private Long id;
    private String sex;
    private String tel;
    private String motto;
    private String grade;
}

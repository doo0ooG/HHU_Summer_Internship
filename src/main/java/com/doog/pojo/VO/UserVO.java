package com.doog.pojo.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private Long id;

    private String username;

    private String sex;

    private String tel;

    private Integer per;

    private String motto;

    private String grade;

    private String clubName;

    private String photoPath;

}

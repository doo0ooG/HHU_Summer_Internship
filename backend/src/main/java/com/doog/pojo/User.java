package com.doog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String sex;

    private String tel;

    private Integer per;

    private String motto;

    private String grade;

    private Long clubId;

    private String photoPath;

}

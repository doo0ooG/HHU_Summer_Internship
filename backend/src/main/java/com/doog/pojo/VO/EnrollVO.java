package com.doog.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollVO {
    private Long id;
    private Long userId;
    private Long activityId;
    private Date date;
    private String username;
    private String activityName;
}

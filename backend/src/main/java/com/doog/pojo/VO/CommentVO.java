package com.doog.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private Long id;
    private Long activityId;
    private Long userId;
    private String info;
    private Date date;
    private String username;
    private String photoPath;
    private String activityName;
}

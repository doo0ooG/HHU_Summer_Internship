package com.doog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enroll {
    Long id;
    Long activityId;
    Long userId;
    Date date;
}

package com.doog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Activity {
    private Long aId;
    private String aName;
    private Long cId; // Assuming c_id in the table corresponds to the Club's c_id
    private String aInfo;
    private Long uId; // Assuming u_id in the table corresponds to the User's id
    private LocalDateTime aStartDate;
    private LocalDateTime aEndDate;
    private String aPos;
    private String aCate;
    private Integer aLimit;
}
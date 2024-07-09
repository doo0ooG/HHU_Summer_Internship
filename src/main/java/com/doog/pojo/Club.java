package com.doog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Club {
    private Long cId;
    private String cName;
    private String cCate;
    private LocalDateTime cDate;
    private String cInfo;
    private Long uId; // Assuming u_id in the table corresponds to the User's id
}
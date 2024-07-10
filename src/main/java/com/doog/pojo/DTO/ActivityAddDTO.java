package com.doog.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityAddDTO {
    private String name;
    private String info;
    private Date startDate;
    private Date endDate;
    private String pos;
    private String cate;
    private Integer limitNum;
}

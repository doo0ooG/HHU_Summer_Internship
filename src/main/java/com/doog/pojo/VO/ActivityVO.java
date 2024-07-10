package com.doog.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityVO {
    private Long id;

    private String name;

    private String clubName;

    private String info;

    private String hostUsername;

    private Date startDate;

    private Date endDate;

    private String pos;

    private String cate;

    private Integer limitNum;

}
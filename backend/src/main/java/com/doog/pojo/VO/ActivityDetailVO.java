package com.doog.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDetailVO {
    private Long id;

    private String name;

    private Long clubId;

    private String info;

    private Long userId;

    private Date startDate;

    private Date endDate;

    private String pos;

    private String cate;

    private Integer limitNum;

    private String photoPath;

    private Integer enrollNum;
}

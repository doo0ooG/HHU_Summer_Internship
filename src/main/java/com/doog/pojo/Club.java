package com.doog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {

    private Long id;

    private String name;

    private String cate;

    private Date date;

    private String info;

}

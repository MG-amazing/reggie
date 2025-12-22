package com.itheima.reggie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CountTotalMealAndTotalAmountDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Tokyo")
    private LocalDateTime dateBegin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Tokyo")
    private LocalDateTime dateEnd;
}

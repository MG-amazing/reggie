package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.dto.CountTotalMealAndTotalAmountDto;
import com.itheima.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
    List<Orders> getByBeginAndEndDate(CountTotalMealAndTotalAmountDto dto);
}

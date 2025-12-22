package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.dto.CountTotalMealAndTotalAmountDto;
import com.itheima.reggie.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    List<OrderDetail> getByBeginAndEndDate(CountTotalMealAndTotalAmountDto param);
}

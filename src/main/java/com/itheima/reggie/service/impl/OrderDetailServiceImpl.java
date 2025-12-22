package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.dto.CountTotalMealAndTotalAmountDto;
import com.itheima.reggie.entity.OrderDetail;
import com.itheima.reggie.mapper.OrderDetailMapper;
import com.itheima.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper,OrderDetail>implements OrderDetailService {
    @Override
    public List<OrderDetail> getByBeginAndEndDate(CountTotalMealAndTotalAmountDto param) {
        return this.baseMapper.getByBeginAndEndDate(param);
    }
}

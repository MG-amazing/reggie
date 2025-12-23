package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.CountTotalMealAndTotalAmountDto;
import com.itheima.reggie.entity.OrderDetail;
import com.itheima.reggie.entity.Orders;
import com.itheima.reggie.service.OrderDetailService;
import com.itheima.reggie.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/count")
@Api(tags = "统计某个时间段内的总订单数和总金额相关接口")
public class CountTotalController {
    /**
     * TODO
     * 统计某个时间段内的总订单数和总金额
     * 本题100分 难度★★★★★
     * 根据传入的参数，统计某个时间段内的总订单数和总金额
     * 例如：
     * 请求参数：{
     * "dateBegin": "2025-12-17 00:00:00",
     * "dateEnd": "2025-12-23 23:59:59"
     * }
     * 返回结果：
     * {
     * "data1": [
     * { "day": "Day 1", "value": 56 },
     * { "day": "Day 2", "value": 167 },
     * { "day": "Day 3", "value": 61 },
     * { "day": "Day 4", "value": 123 },
     * { "day": "Day 5", "value": 113 },
     * { "day": "Day 6", "value": 10 },
     * { "day": "Day 7", "value": 99 }
     * ],
     * "data2": [
     * { "name": "Search Engine","value": 493 },
     * { "name": "Direct","value": 149 },
     * { "name": "Email","value": 224 },
     * { "name": "Ads","value": 37 }
     * ]
     * }
     * 说明：建议用时5小时 day为日期 value为金额
     * { "day": "Day 1", "value": 56 },
     * name为菜品名字 value为销售菜品数量
     * { "name": "Search Engine","value": 493 },
     * <p>
     * 要求：使用给定的返回格式与类型进行返回Map<String, Object>
     * data1为折线图
     * data2为饼图
     *
     * @param data
     * @return
     */
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public CountTotalController(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }
    @ApiOperation("统计某个时间段内的总订单数和总金额")
    @PostMapping("/countTotalMealAndTotalAmountByDate")
    public R<?> countTotalMealAndTotalAmountByDate(@RequestBody CountTotalMealAndTotalAmountDto param) {
        Map<String, Object> result = new HashMap<>();
        //请在此处编码



        return R.success(result);
    }
}

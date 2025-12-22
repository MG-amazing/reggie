package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.CountTotalMealAndTotalAmountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
@Slf4j
@RequestMapping("/countTotalMealAndTotalAmount")
public class CountTotalController {
    /**
     * 统计某个时间段内的总订单数和总金额
     * 本题100分
     * 根据传入的参数，统计某个时间段内的总订单数和总金额
     例如：
     请求参数：{dateBegin: "2023-01-01", dateEnd: "2023-01-31"}
     * @param data
     * @return
     */
    @PostMapping("/countTotalMealAndTotalAmountByDate")
    public R<Map<String, Object>> countTotalMealAndTotalAmountByDate(@RequestBody CountTotalMealAndTotalAmountDto data) {
        Map<String, Object> result = new HashMap<>();

        return  R.success(result);
    }
}

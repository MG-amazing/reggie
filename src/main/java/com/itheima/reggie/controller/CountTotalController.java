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
    @PostMapping("/countTotalMealAndTotalAmountByDate")
    public R<Map<String, Object>> countTotalMealAndTotalAmountByDate(@RequestBody CountTotalMealAndTotalAmountDto data) {
        Map<String, Object> result = new HashMap<>();

        return  R.success(result);
    }
}

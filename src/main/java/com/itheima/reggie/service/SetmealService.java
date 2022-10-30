package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    //新增套餐,同时保存套餐和菜品的关联关系
    public void saveWithDish(SetmealDto setmealDto);
    //删除菜品和关联的数据
    public void removeWithDish(List<Long>ids);
    /**
     * 根据套餐id修改售卖状态
     * @param status
     * @param ids
     */
    public void updateSetmealStatusById(Integer status, List<Long> ids);

    public  SetmealDto getDate(Long id);
}

package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@Api(tags = "员工相关接口")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param request
     * @param employee
     * @return
     */
    @ApiOperation("员工登录接口")
    @PostMapping("/login")
    public R<?> login(HttpServletRequest request, @RequestBody Employee employee) {
        //将页面提交的密码进行MD5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //根据提交的用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //如果失败返回登录失败的结果
        if (emp == null) {
            return R.error("登录失败");
        }
        //进行密码比对
        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败");
        }
        //密码比对成功
        //比对员工状态
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }
        //没有禁用表示登录成功
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出
     *
     * @param request
     * @return
     */
    @ApiOperation("员工退出接口")
    @PostMapping("/logout")
    public R<?> logout(HttpServletRequest request) {
        //清除Session中保存的当前登录员工的Id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @ApiOperation("新增员工接口")
    @PostMapping
    public R<?> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工,员工信息{}", employee.toString());
        //设置初始密码,进行MD5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //创建人
        //Long empId = (Long) request.getSession().getAttribute("employee");
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @ApiOperation("员工信息分页查询接口")
    @GetMapping("/page")
    public R<?> pageR(int page, int pageSize, String name) {
        log.info("page={},pageSize{},name{}", page, pageSize, name);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构建条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @ApiOperation("根据id修改员工信息接口")
    @PutMapping
    public R<?> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());


        long id=Thread.currentThread().getId();
        log.info("线程id为{}",id);
        //Long empId = (Long) request.getSession().getAttribute("employee");
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @ApiOperation("根据id查询员工信息接口")
    @GetMapping("/{id}")
    public R<?> getById(@PathVariable Long id){
        log.info("根据id查询员工信息");
        Employee employee = employeeService.getById(id);
        if (employee!=null){
            return R.success(employee);
        }
        return R.error("没有查询到员工信息");
    }
}

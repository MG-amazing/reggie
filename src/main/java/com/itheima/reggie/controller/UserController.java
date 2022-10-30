package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.User;
import com.itheima.reggie.service.UserService;
import com.itheima.reggie.utils.SmsUtil;
import com.itheima.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 发送短信验证码
     *
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
        boolean okPhone = phone.matches("^(13[0-9]{9})|(15[0-9]{9})|(17[0-9]{9})|(18[0-9]{9})|(19[0-9]{9})$");
        if (okPhone && StringUtils.isNotEmpty(phone)) {
            //生成随机四位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            //此方法慎用一次5毛钱啊
            //调用短信API发送短信即可
            //SmsUtil.sendMessage(phone,code,"30","2e65b1bb3d054466b82f0c9d125465e2", "908e94ccf08b4476ba6c876d13f084ad");

            //将生成的验证码保存到Session中
            //session.setAttribute(phone, code);

            //将生成的验证码存储到Redis中,并设置有效时长为5分钟
            redisTemplate.opsForValue().set(phone,code,30, TimeUnit.MINUTES);
            return R.success("手机验证码发送成功");
        }

        return R.error("短信发送失败,请检查手机号是否填写正确");
    }


    /**
     * 移动端用户登录
     *
     * @param map
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info("map:{}", map);
        //获取手机号
        String phone = (String) map.get("phone");
        //获取验证码
        String code = map.get("code").toString();
        //从Session比对


        //Object codeInSession = session.getAttribute(phone);
        //从Redis中获取缓存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);


        if (codeInSession != null && codeInSession.equals(code)) {
            //如果比对成功,则登录成功

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                //判断手机号对应的手机号是否是新用户,如果是新用户自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }

            session.setAttribute("user",user.getId());
            //用户登录成功,删除Redis中缓存的验证码
            redisTemplate.delete(phone);

            return R.success(user);


        }

        return R.error("登录失败");
    }
    /**
     * 退出功能
     * ①在controller中创建对应的处理方法来接受前端的请求，请求方式为post；
     * ②清理session中的用户id
     * ③返回结果（前端页面会进行跳转到登录页面）
     * @return
     */
    @PostMapping("/loginout")
    public R<String> logout(HttpServletRequest request){
        //清理session中的用户id
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }

}

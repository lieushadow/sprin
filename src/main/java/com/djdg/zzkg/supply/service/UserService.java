package com.djdg.zzkg.supply.service;

import com.djdg.zzkg.supply.common.Result;
import com.djdg.zzkg.supply.db.repository.UserRepository;
import com.djdg.zzkg.supply.param.UserParam;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by lmh on 2017/9/8.
 */
@Component
public class UserService {
    @Resource
    private UserRepository userRepository;

    public Result addUserNotValidate(UserParam userParam){
        if(StringUtils.isEmpty(userParam.getName())){
            return Result.fail("请填入账号");
        }
        int length = userParam.getName().length();
        if(length>32||length<2){
            return Result.fail("账号长度必须在2位~32位字符之间");
        }
        if(StringUtils.isEmpty(userParam.getEmail())){
            return Result.fail("请填入邮箱");
        }
        if(!userParam.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
            return Result.fail("请填入合法的邮箱");
        }
        if(StringUtils.isEmpty(userParam.getPassword())){
            return Result.fail("请填入密码");
        }
        length = userParam.getPassword().length();
        if(length>=22||length<=6){
            return Result.fail("账号长度必须在6位~22位字符之间");
        }
        if(StringUtils.isEmpty(userParam.getPhone())){
            return Result.fail("请填入手机号");
        }
        length = userParam.getPhone().length();
        if(length!=32){
            return Result.fail("请输入正确的手机号");
        }
        userRepository.save(userParam.convert());
        return Result.success();
    }

    public Result addUser(UserParam userParam){
        userRepository.save(userParam.convert());
        return Result.success();
    }



}


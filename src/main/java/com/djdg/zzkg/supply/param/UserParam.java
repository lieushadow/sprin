package com.djdg.zzkg.supply.param;


import com.djdg.zzkg.supply.constraints.Add;
import com.djdg.zzkg.supply.constraints.Modify;
import com.djdg.zzkg.supply.db.entity.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lmh on 2017/9/12.
 */
public class UserParam {

    @NotNull(message = "要修改的用户部存在",groups = {Modify.class})
    private Long userId;
    @NotNull(message = "请输入用户名")
    @Size(min = 2,max = 32,message = "用户名需要在2~32位之间")
    private String name;
    @NotNull(message = "请输入密码")
    @Size(min = 6,max = 20,message = "用户名需要在6~20位之间",groups = {Add.class})
    private String password;
    @NotNull(message = "请输入手机号")
    @Size(min = 11,max = 11,message = "请输入合法的手机号")
    private String phone;
    @NotNull(message = "请输入邮箱")
    @Email(message = "请输入合法的邮箱")
    private String email;

    public UserParam() {
    }

    public User convert(){
        User user = new User();
        BeanUtils.copyProperties(this,user);
        return user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

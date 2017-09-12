package com.djdg.zzkg.supply.api;

import com.djdg.zzkg.supply.common.Result;
import com.djdg.zzkg.supply.constraints.Add;
import com.djdg.zzkg.supply.param.UserParam;
import com.djdg.zzkg.supply.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by lmh on 2017/9/12.
 */
@Component
@Path("/user")
public class UserApi {

    private UserService userService;

    @Path("/addNotValidate")
    @POST
    public Result addUserNotValidate(UserParam userParam){
        return userService.addUserNotValidate(userParam);
    }

    @Path("/add")
    @POST
    public Result addUser(@Valid UserParam userParam){
        return userService.addUser(userParam);
    }

    @Path("/addWithGroup")
    @POST
    public Result addWithGroup(@Validated(Add.class) UserParam userParam){
        return userService.addUser(userParam);
    }





}

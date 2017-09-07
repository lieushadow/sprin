package com.djdg.zzkg.supply.api;

import com.djdg.zzkg.supply.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * SampleUser:刘敏华 shadow.liu@hey900.com
 * DateDeserializer: 2017-08-07
 * Time: 16:07
 */
@Component
@Path("/test/")
@Api(value = "测试",description = "测试使用接口")
@Produces({MediaType.APPLICATION_JSON})
public class TestApi {

    @GET
    @Path("/hello")
    @ApiOperation(value = "/hello",notes = "测试接口")
    public Result sayHello(){
        return Result.success("hello",null);
    }



}

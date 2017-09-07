package com.djdg.zzkg.supply.filter;

import com.djdg.zzkg.supply.common.Result;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:参数校验全局处理
 * SampleUser:刘敏华 shadow.liu@hey900.com
 * DateDeserializer: 2017-08-08
 * Time: 13:44
 */
@Component
@Provider
public class ParamMapping implements ExceptionMapper<ResteasyViolationException> {

    @Override
    public Response toResponse(ResteasyViolationException restEasyViolationException ) {
        List<ResteasyConstraintViolation> violations = restEasyViolationException.getViolations();
        ResteasyConstraintViolation resteasyConstraintViolation = null;
        if(violations!=null&&!violations.isEmpty()){
            resteasyConstraintViolation = violations.get(0);
        }
        return Response.ok(Result.fail(resteasyConstraintViolation.getMessage()), MediaType.APPLICATION_JSON_TYPE).build();
    }
}

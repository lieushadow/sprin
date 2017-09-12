package com.djdg.zzkg.supply.constraints;

import com.djdg.zzkg.supply.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lmh on 2017/9/12.
 */
@ControllerAdvice
public class ParamException {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result validateErrorHandler(BindException ex) {
        return Result.fail(ex.getAllErrors().get(0).getDefaultMessage());
    }
}

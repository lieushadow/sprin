package com.djdg.zzkg.supply.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lmh on 2017/9/12.
 */

@Constraint(validatedBy = PhoneValidator.class) //具体的实现
@Target( { ElementType.METHOD,
        ElementType.FIELD })
@Retention(RUNTIME)
@Documented
public @interface Phone {

    String message() default "{phone.length}"; //提示信息,可以写死,可以填写国际化的key

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int length() default 11;
}

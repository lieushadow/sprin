package com.djdg.zzkg.supply.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by lmh on 2017/9/12.
 */
public class PhoneValidator implements ConstraintValidator<Phone,String>{

    int length;

    @Override
    public void initialize(Phone phoneValidator) {
        length = phoneValidator.length();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() == length;
    }
}

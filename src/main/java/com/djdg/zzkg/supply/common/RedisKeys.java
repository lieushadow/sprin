package com.djdg.zzkg.supply.common;

import java.text.MessageFormat;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * UserTest:刘敏华 shadow.liu@hey900.com
 * Date: 2017-08-09
 * Time: 14:48
 */
public class RedisKeys {

    public static String key4Pwd(Long userId){
        return MessageFormat.format("pwdFail:{0}:count", userId);
    }

    public static String key4UserToken(String  token){
        return MessageFormat.format("session:{0}", token);
    }
}

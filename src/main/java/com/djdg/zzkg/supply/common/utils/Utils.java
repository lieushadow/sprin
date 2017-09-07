package com.djdg.zzkg.supply.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * UserTest:刘敏华 shadow.liu@hey900.com
 * Date: 2017-08-10
 * Time: 10:10
 */
public class Utils {


    public static String md5(String md5){
        if(StringUtils.isEmpty(md5)) return StringUtils.EMPTY;
        return DigestUtils.md5Hex(md5);
    }

    public static String md5(byte[] md5)
    {
        if(md5==null) return StringUtils.EMPTY;

        return DigestUtils.md5Hex(md5);
    }

    public static String genRandomNumberCode(int length) {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code += random.nextInt(10);
        }
        return code;
    }


    final static char RANDOMSTRCODE[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', '!', '@', '#', '$', '%', '&', '(', ')', '-', '='};

    public static String genRandomStrCode(int length) {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(RANDOMSTRCODE.length);
            code += RANDOMSTRCODE[index];
        }
        return code;

    }



}

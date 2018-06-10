package com.ting.order.utils;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:生成唯一主键
 * User: ting
 * Date: 2018-05-19
 * Time: 下午11:51
 */
public class KeyUtil {
    /*格式：时间+随机数,不能保证唯一*/
    public static synchronized  String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}

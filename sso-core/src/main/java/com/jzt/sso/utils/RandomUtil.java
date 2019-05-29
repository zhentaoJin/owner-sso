/**
 * FileName: RandomUtil
 * Author:   liujh
 * Date:     2018/8/14 16:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sso.utils;

import java.util.Random;

public class RandomUtil {
    public static Random random = new Random();

    public RandomUtil() {
    }

    public static String getRandom(int length) {
        StringBuilder ret = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            boolean isChar = random.nextInt(2) % 2 == 0;
            if (isChar) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                ret.append((char)(choice + random.nextInt(26)));
            } else {
                ret.append(Integer.toString(random.nextInt(10)));
            }
        }

        return ret.toString();
    }

    public static String getRandomNum(int length) {
        StringBuilder ret = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            ret.append(Integer.toString(random.nextInt(10)));
        }

        return ret.toString();
    }
}

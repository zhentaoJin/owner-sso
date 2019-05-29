package com.jzt.sso.common.utils;

import java.util.Random;

public class RandomUtil {

    // 产生+8位随机数
    public static String createRandomCode(String prefix, int num) {
        StringBuilder str = new StringBuilder();// 定义变长字符串
        Random random = new Random();
        // 随机生成数字，并添加到字符串
        for (int i = 0; i < num; i++) {
            str.append(random.nextInt(10));
        }
        return prefix + str;
    }
}

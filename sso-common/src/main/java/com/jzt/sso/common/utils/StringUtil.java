package com.jzt.sso.common.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

    /**
     * 属性使用驼峰命名，改成数据库字段名，有大写的字母要加下划线“_”改成小写 例：convertPropertyToColumn("createTime") return "create_time";
     *
     * @param propertyName
     * @return
     */
    public static String convertPropertyToColumn(String propertyName) {
        if (propertyName == null) {
            return null;
        }
        int length = propertyName.length();
        if (length < 2) {
            return propertyName.toLowerCase();
        }

        char[] charArray = propertyName.toCharArray();
        int num = 0;
        for (char c : charArray) {
            if (c >= 65 && c <= 90) {
                num++;
            }
        }
        int resultLength = length + num;
        char[] resultCharArray = new char[resultLength];
        int j = 0;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c >= 65 && c <= 90) {
                resultCharArray[i + j] = '_';
                j++;
                resultCharArray[i + j] = (char)(charArray[i] + 32);
            } else {
                resultCharArray[i + j] = charArray[i];
            }
        }

        return new String(resultCharArray);
    }

    public static void main(String[] args) {
        // String pa = "^\\d+(\\.\\d+){0,2}$";
        // Pattern compile = Pattern.compile(pa);
        // Matcher matcher = compile.matcher("aaaa1.1.2.1");
        // System.out.println(matcher.find());
        // 要比较的两个字符串
        String str1 = "v_dept_code";
        str1 = str1.replaceAll("_", "");
        String str2 = "deptCode";
        levenshtein(str1, str2);
    }

    // 比较字符串相似度
    private static float levenshtein(String str1, String str2) {
        // 计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        // 建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        // 赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        // 计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1, dif[i - 1][j] + 1);
            }
        }

        // 取数组右下角的值，同样不同位置代表不同字符串的比较

        // 计算相似度
        return 1 - (float)dif[len1][len2] / Math.max(str1.length(), str2.length());
    }

    // 得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }
}

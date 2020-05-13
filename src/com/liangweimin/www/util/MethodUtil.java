package com.liangweimin.www.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 一些方法的工具类
 *
 * @author 梁伟民
 */
public class MethodUtil {

    /**
     * 根据文件名判断有没有文件
     *
     * @param fileName
     * @return 布尔值
     */
    public static boolean haveFile(String fileName) {
        System.out.println(fileName);
        return fileName != null && !"".equals(fileName);
    }


    /**
     * 修改文件名为当前时间+随机数
     * @param fileName
     * @return 文件名字符串
     */
    public static String getNewFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        String formatDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String s = String.valueOf(new Random().nextInt(100000));
        return formatDate + s + "." + ext;
    }


    /**
     * 一个用来判断输入字符串是否为数字的方法
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {

            //isDigit(char ch)判断字符是否是数字
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }

        }
        //str不能为""
        return !"".equals(str);
    }
}

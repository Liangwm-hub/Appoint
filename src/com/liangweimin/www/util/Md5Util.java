package com.liangweimin.www.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5密码加密工具类
 *
 * @author 梁伟民
 */
public class Md5Util {
    public static String md5Encrypt(String password) {

        if (password != null) {

            // MessageDigest 类为应用程序提供信息摘要算法的功能
            MessageDigest md5 = null;

            try {
                //返回实现指定摘要算法(md5)的MessageDigest对象
                md5 = MessageDigest.getInstance("md5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            StringBuilder str = new StringBuilder();

            //使用指定的字节数组对摘要执行最终更新，然后完成摘要计算
            byte[] by = new byte[0];
            if (md5 != null) {
                by = md5.digest(password.getBytes());
            }

            //将字节转成字符串
            for (byte b : by) {
                str.append(b);
            }

            //返回str
            return str.toString();
        } else {
            return null;
        }

    }
}

package com.liangweimin.www.test;

import com.liangweimin.www.util.Md5Util;

/**
 * @author 梁伟民
 */
public class TestMd5 {
    public static void main(String[] args) {
        String encrypt = Md5Util.md5Encrypt("12345");
        //-126124-5314-22-1181121087652-95104-111-878123
        System.out.println(encrypt);

        String pwd = "12a@ss1122222dew1.dsjdio23788wdgw6d222222223c";
        String encrypt2 = Md5Util.md5Encrypt(pwd);
        //1518113104-85-1082371-11822-551981-73-11224
        System.out.println(encrypt2);
    }
}

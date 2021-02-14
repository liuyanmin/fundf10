package com.lym.util;

import lombok.extern.log4j.Log4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liuyanmin on 2019/10/20.
 */
@Log4j
public class Md5Util {
    private static char[] hexDigist = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    public static String md5(String buffer) {
        String string;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(buffer.getBytes());
            byte[] datas = md.digest(); //16个字节的长整数

            char[] str = new char[2*16];
            int k = 0;

            for(int i = 0; i < 16; i++) {
                byte b   = datas[i];
                str[k++] = hexDigist[b>>>4 & 0xf];//高4位
                str[k++] = hexDigist[b & 0xf];//低4位
            }
            string = new String(str);
        } catch (NoSuchAlgorithmException e) {
            log.error("生成MD5失败: ", e);
            return buffer;
        }
        return string;
    }

}

package com.framework.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * ��Ŀ���ƣ�anyitongWeb
 * �����ƣ�
 * ��������
 * �����ˣ�Administrator
 * ����ʱ�䣺2015/7/11 15:54
 * �޸��ˣ�Administrator
 * �޸�ʱ�䣺2015/7/11 15:54
 * �޸ı�ע��
 */
public final class MD5 {
    private static Logger logger = LoggerFactory.getLogger(MD5.class);

    public static String convert(String s) {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = s.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[(k++)] = hexChars[(b >>> 4 & 0xF)];
                chars[(k++)] = hexChars[(b & 0xF)];
            }
            return new String(chars);
        } catch (Exception e) {
            logger.error("MD5 Generate error", e);
        }
        return null;
    }
}

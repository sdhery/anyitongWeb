package com.framework.common.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 项目名称：anyitongWeb
 * 类名称：
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2015/7/11 12:09
 * 修改人：Administrator
 * 修改时间：2015/7/11 12:09
 * 修改备注：
 */
public class DigestUtil {
    /**
     * Calculate digest of given file with given algorithm.
     * Writes digest to file named filename.algorithm .
     *
     * @param filename  the String name of the file to be hashed
     * @param algorithm the algorithm to be used to compute the digest
     */
    public static void digestFile(String filename, String algorithm) {
        byte[] b = new byte[65536];
        int count = 0;
        int read = 0;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            fis = new FileInputStream(filename);
            while (fis.available() > 0) {
                read = fis.read(b);
                md.update(b, 0, read);
                count += read;
            }
            byte[] digest = md.digest();
            StringBuffer fileNameBuffer = new StringBuffer(128).append(filename).append(".").append(algorithm);
            fos = new FileOutputStream(fileNameBuffer.toString());
            OutputStream encodedStream = MimeUtility.encode(fos, "base64");
            encodedStream.write(digest);
            fos.flush();
        } catch (Exception e) {
            System.out.println("Error computing Digest: " + e);
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Calculate digest of given String using given algorithm.
     * Encode digest in MIME-like base64.
     *
     * @param pass      the String to be hashed
     * @param algorithm the algorithm to be used
     * @return String Base-64 encoding of digest
     * @throws NoSuchAlgorithmException if the algorithm passed in cannot be found
     */
    public static String digestString(String pass, String algorithm) throws
            NoSuchAlgorithmException {

        MessageDigest md;
        ByteArrayOutputStream bos;

        try {
            md = MessageDigest.getInstance(algorithm);
            byte[] digest = md.digest(pass.getBytes("iso-8859-1"));
            bos = new ByteArrayOutputStream();
            OutputStream encodedStream = MimeUtility.encode(bos, "base64");
            encodedStream.write(digest);
            return bos.toString("iso-8859-1");
        } catch (IOException ioe) {
            throw new RuntimeException("Fatal error: " + ioe);
        } catch (MessagingException me) {
            throw new RuntimeException("Fatal error: " + me);
        }
    }

    public static String digestString2(String pass, String algorithm) throws
            NoSuchAlgorithmException {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance(algorithm);
            byte[] digest = md.digest(pass.getBytes());
            byte[] dest = new byte[8];
            System.arraycopy(digest, 4, dest, 0, 8);
            char[] pwd = org.apache.commons.codec.binary.Hex.encodeHex(dest);
            return new String(pwd);
        } catch (Exception ioe) {
            throw new RuntimeException("Fatal error: " + ioe);
        }
    }
}

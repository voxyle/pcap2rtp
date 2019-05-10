package com.xp.jrtp.tool;

/**
 *  byte工具
 */
public class ByteTool {
    //把byte数组转换成十六进制
    public static String tohex(byte[] buffer) {
        StringBuffer port = new StringBuffer();
        for (byte b : buffer) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() < 2) {
                hex = "0" + hex;
            }
            port.append(hex);
        }
        return port.toString();
    }
    //把byte数组转换成十六进制并翻转
    public static long toReverseHex(byte[] buffer) {
        StringBuffer strBf = new StringBuffer(tohex(buffer));
        strBf.reverse();
        return Long.parseLong(strBf.toString(), 16);
    }

    /**
     * 把byte转为字符串的bit
     */
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

    /**
     *  合并byte数组;
     * @param b1
     * @param b2
     * @return
     */
    public static byte[] mergeByte(byte[] b1, byte[] b2) {
        byte[] temp = new byte[b1.length + b2.length];
        System.arraycopy(b1, 0, temp, 0, b1.length);
        System.arraycopy(b2, 0, temp, b1.length, b2.length);
        return temp;
    }
}

package com.xp.jrtp.tool;

public class ByteTool {
    public static String tohex(byte[] buff_2) {
        StringBuffer port = new StringBuffer();
        for (byte b : buff_2) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() < 2) {
                hex = "0" + hex;
            }
            port.append(hex);
        }
        return port.toString();
    }

    public static long toReverseHex(byte[] buffer) {
        StringBuffer strBf = new StringBuffer();
        for (byte b : buffer) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() < 2) {
                hex = "0" + hex;
            }
            strBf.insert(0, hex);
        }
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

    public static byte[] mergeByte(byte[] b1, byte[] b2) {
        byte[] temp = new byte[b1.length + b2.length];
        System.arraycopy(b1, 0, temp, 0, b1.length);
        System.arraycopy(b2, 0, temp, b1.length, b2.length);
        return temp;
    }
}

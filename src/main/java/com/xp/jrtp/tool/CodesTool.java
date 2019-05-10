package com.xp.jrtp.tool;


import org.restcomm.media.codec.g729.Decoder;
import org.restcomm.media.codec.g729.Encoder;

import java.util.ArrayList;

public class CodesTool {
    public static byte[] g711DecodeByte(byte[] g711Buffer) {
        int length = g711Buffer.length;
        byte[] pcmBuffer = new byte[length * 2];
        for (int i = 0; i < length; i++) {
            byte alaw = g711Buffer[i];
            alaw ^= 0xD5;

            int sign = alaw & 0x80;
            int exponent = (alaw & 0x70) >> 4;
            int value = (alaw & 0x0F) >> 4 + 8;
            if (exponent != 0) {
                value += 0x0100;
            }
            if (exponent > 1) {
                value <<= (exponent - 1);
            }
            value = (char) ((sign == 0 ? value : -value) & 0xFFFF);
            pcmBuffer[i * 2 + 0] = (byte) (value & 0xFF);
            pcmBuffer[i * 2 + 1] = (byte) (value >> 8 & 0xFF);
        }
        return pcmBuffer;
    }

    /**
     * 解压G729的工具类
     * 参数1:服务器上传的一段压缩完的G729数据,解压完得数据是未解压得16倍，jar包解压和压缩得规则
     * 是以10个字节为一个单位，压缩完是160个字节，一个单位一个单位的压缩，最后在拼接
     */

    public static byte[] g729DecodeByte(byte[] bytes) {
        Decoder decoder = new Decoder();
        byte[] bb = new byte[bytes.length * 16];
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < bytes.length / 10; i++) {
            byte[] b = {bytes[i * 10], bytes[1 + i * 10], bytes[2 + i * 10], bytes[3 + i * 10], bytes[4 + i * 10], bytes[5 + i * 10], bytes[6 + i * 10], bytes[7 + i * 10], bytes[8 + i * 10], bytes[9 + i * 10]};
            byte[] process = decoder.process(b);
            ArrayList<Byte> arr = new ArrayList<>();
            for (int j = 0; j < process.length; j++) {
                arr.add(process[j]);
            }
            list.addAll(arr);
        }
        for (int i = 0; i < list.size(); i++) {
            bb[i] = list.get(i);
        }
        return bb;
    }

    /**
     * 压缩跟解压正好想法，把数据拆分成160个字节为一单位，解压成10个字节，最后在拼接
     */
    public static byte[] g729EncodeByte(byte[] bytes) {
        Encoder encoder = new Encoder();
        byte[] bb = new byte[bytes.length / 16];
        ArrayList<Byte> list = new ArrayList<>();//没有解压的集合
        ArrayList<Byte> list2 = new ArrayList<>();//解压完的集合
        for (int i = 0; i < bytes.length; i++) {
            list.add(bytes[i]);//为集合添加数据
        }
        for (int i = 0; i < list.size() / 160; i++) {
            byte[] b = new byte[160];//创建160为基准的小byte【】
            for (int j = 0; j < 160; j++) {
                b[j] = list.get(i * 160 + j);
            }
            byte[] process = encoder.process(b);
            for (int j = 0; j < process.length; j++) {
                list2.add(process[j]);
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            bb[i] = list2.get(i);
        }
        return bb;
    }
}

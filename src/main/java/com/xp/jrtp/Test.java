package com.xp.jrtp;

import com.xp.jrtp.tool.PCMTool;

import java.io.*;
import java.util.Objects;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println(Objects.hash("123", "321"));
        System.out.println(Objects.hash("321", "123"));
//        byte[] pcm1 =getByte("D:\\goProject\\rtp2wav\\850678569881→113634996719_52db0811.g729_18");
//        byte[] pcm2 =getByte("D:\\goProject\\rtp2wav\\241102049918→513807087555_3e586f93.g729_18");
//        byte[]pcm = PCMTool.merge(pcm1,pcm2);
//        FileOutputStream fos = new FileOutputStream("D:\\goProject\\rtp2wav\\pcm12");
//        fos.write(pcm);
//        fos.flush();
//        fos.close();
    }

    private static byte[] getByte(String path) throws IOException {
        FileInputStream fos =
                 null;
        fos = new FileInputStream(path);
        ByteArrayOutputStream bos  = new ByteArrayOutputStream();
        byte[] temp = new byte[1024];
        int len = 0;
        while ((len = fos.read(temp))!=-1){
            bos.write(temp,0,len);
        }
        bos.close();
        fos.close();
       return bos.toByteArray();
    }
}

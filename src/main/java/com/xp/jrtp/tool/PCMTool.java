package com.xp.jrtp.tool;

/**
 * pcm工具
 */
public class PCMTool {
    /**
     * 对两段音频进行混合
     * @param pcm1
     * @param pcm2
     * @return
     */
    public static byte[] merge(byte[] pcm1, byte[] pcm2) {
        int max = Math.max(pcm1.length, pcm2.length);
        byte[] temp = new byte[max];
        for (int i = 0; i < max; i++) {
            if (i >= pcm1.length) {
                temp[i] = pcm2[i];
                continue;
            }
            if (i >= pcm2.length) {
                temp[i] = pcm1[i];
                continue;
            }
            temp[i] = (byte) (pcm1[i] + pcm2[i]);
        }
        return temp;
    }
}

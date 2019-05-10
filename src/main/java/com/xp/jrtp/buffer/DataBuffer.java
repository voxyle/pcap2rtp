package com.xp.jrtp.buffer;

/**
 * 字节读取Buffer
 */
public class DataBuffer {
    private byte[] data;
    private int offset;
    private int length;

    public static DataBuffer wrap(byte[] data) {
        return new DataBuffer(data);
    }

    public DataBuffer(byte[] data) {
        this.data = data;
        this.length = data.length;
        this.offset = 0;
    }

    public DataBuffer(byte[] data, int start, int end) {
        this.data = new byte[end];
        System.arraycopy(data, start, this.data, 0, end);
        this.length = end;
        this.offset = 0;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * 偏移量
     *
     * @param offset
     */
    public void offset(int offset) {
        this.offset += offset;
    }

    public void restOffset() {
        this.offset = 0;
    }

    public byte get() {
        return data[offset++];
    }

    public void get(byte[] dest) {
        System.arraycopy(data, offset, dest, 0, dest.length);
        offset += dest.length;
    }

    public void getBuf_2(byte[] dest) {
        System.arraycopy(data, offset, dest, 0, 2);
        offset += 2;
    }

    public void getBuf_4(byte[] dest) {
        System.arraycopy(data, offset, dest, 0, 4);
        offset += 4;
    }

    public byte[] array() {
        int dlen = length - offset;
        byte[] dest = new byte[dlen];
        System.arraycopy(data, offset, dest, 0, dlen);
        offset += dlen;
        return dest;
    }
}

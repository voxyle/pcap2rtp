package com.xp.jrtp.voip;

public class Rtp implements Comparable<Rtp> {
    //必须是2 (2位)
    private int version;
    // 是否有paddind(1位)
    //0 false 1 ture
    private int padding;
    // 是否有扩展标题(1位)
    //0 false 1 ture
    private int extHeader;
    //CSRC 个数(4位)
    private int cc;
    //标志位 (1位)
    private int maker;
    //payload type (7位)
    private int pType;
    //序列号(2字节)顺序
    // 每个RTP packet发送后该序列号加1，接收方可以根据该序列号重新排列数据包顺序。
    private int sNumber;
    //rtp时间戳需要转换(4字节)
    private long timestamp;
    //RTP packet 唯一标识(4字节)
    private String ssrc;
    //语音数据
    private byte[] payload;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getExtHeader() {
        return extHeader;
    }

    public void setExtHeader(int extHeader) {
        this.extHeader = extHeader;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public int getMaker() {
        return maker;
    }

    public void setMaker(int maker) {
        this.maker = maker;
    }

    public int getpType() {
        return pType;
    }

    public void setpType(int pType) {
        this.pType = pType;
    }

    public int getsNumber() {
        return sNumber;
    }

    public void setsNumber(int sNumber) {
        this.sNumber = sNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSsrc() {
        return ssrc;
    }

    public void setSsrc(String ssrc) {
        this.ssrc = ssrc;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    /**
     * 设置比较器,对rtp包进行排序
     *
     * @param o
     * @return
     */
    public int compareTo(Rtp o) {
        int csn = o.getsNumber();
        return sNumber - csn ;
    }
}

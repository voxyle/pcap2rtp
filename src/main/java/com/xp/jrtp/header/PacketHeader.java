package com.xp.jrtp.header;

/**
 * 数据包头
 */
public class PacketHeader {
    private long timeHi;//时间戳高位  秒级
    private long timeLo;//时间戳低位  微秒级
    private long cLen;//数据长度
    private long dLen;//离线数据长度

    public long getTimeHi() {
        return timeHi;
    }

    public void setTimeHi(long timeHi) {
        this.timeHi = timeHi;
    }

    public long getTimeLo() {
        return timeLo;
    }

    public void setTimeLo(long timeLo) {
        this.timeLo = timeLo;
    }

    public long getcLen() {
        return cLen;
    }

    public void setcLen(long cLen) {
        this.cLen = cLen;
    }

    public long getdLen() {
        return dLen;
    }

    public void setdLen(long dLen) {
        this.dLen = dLen;
    }




}

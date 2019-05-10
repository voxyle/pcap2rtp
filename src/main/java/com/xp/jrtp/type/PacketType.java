package com.xp.jrtp.type;

/**
 * pcap包类型
 */
public enum  PacketType {
    SEND(4),   //发送的包
    UNICAST(0); //单播 (不是很懂)
    int type = 0;
    PacketType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}

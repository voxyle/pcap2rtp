package com.xp.jrtp.type;

public enum  PacketType {
    SEND(4),
    UNICAST(0);
    int type = 0;
    PacketType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}

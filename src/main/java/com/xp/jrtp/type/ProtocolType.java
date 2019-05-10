package com.xp.jrtp.type;

/**
 *  协议类型
 * @author voxyle
 */
public enum ProtocolType {

    OTHER(0),                // 其他协议号默认为0
    TCP(6),                    // TCP 协议号：6
    UDP(17);                    // UDP 协议号：17

    private int value;

    ProtocolType(int value) {
        this.value = value;
    }
    public static ProtocolType parseType(int value) {
        for (ProtocolType pt : ProtocolType.values()) {
            if (pt.value == value)
                return pt;
        }
        return OTHER;
    }

}

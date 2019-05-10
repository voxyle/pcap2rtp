package com.xp.jrtp.type;

/**
 * Э������
 *
 * @author johnnie
 */
public enum ProtocolType {

    OTHER(0),                // ����Э��ţ�Ĭ��Ϊ0
    TCP(6),                    // TCP Э��ţ�6
    UDP(17);                    // UDP Э��ţ�17

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

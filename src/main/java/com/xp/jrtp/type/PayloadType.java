package com.xp.jrtp.type;

public enum PayloadType {
    GSM(3),
    g711(8),
    g729(18),
    g722(9),
    h263(34),
    other(0);
    private int value;

    PayloadType(int value) {
        this.value = value;
    }
    public static PayloadType parseType(int value) {
        for (PayloadType pt : PayloadType.values()) {
            if (pt.value == value)
                return pt;
        }
        return other;
    }
}

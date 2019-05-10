package com.xp.jrtp.type;

/**
 * payload类型
 */
public enum PayloadType {
    GSM(3),  //GSM 类型码: 3
    g711(8), //G711 类型码: 8
    g729(18),//G729 类型码: 18
    g722(9), //G722 类型码: 9
    h263(34),//H263 类型码: 34
    other(0);//其他类型默认为0
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

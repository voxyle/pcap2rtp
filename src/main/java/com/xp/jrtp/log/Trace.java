package com.xp.jrtp.log;

/**
 * log
 */
public class Trace {
    private static final boolean debug = true;

    public static void i(String msg) {
        if (debug) {
            System.out.println(msg);
        }
    }
}



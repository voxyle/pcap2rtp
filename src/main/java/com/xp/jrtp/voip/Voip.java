package com.xp.jrtp.voip;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Voip {
    private List<Sip> sipArray;
    private List<Rtp> rtpArray;

    public Voip() {
        sipArray = new ArrayList<>();
        rtpArray = new ArrayList<Rtp>();
    }

    public List<Sip> getSip() {
        return sipArray;
    }

    public void setSip(Sip sip) {
        sipArray.add(sip);
    }

    public List<Rtp> getRtp() {
        return rtpArray;
    }

    public void setRtp(Rtp rtp) {
        rtpArray.add(rtp);
    }
}

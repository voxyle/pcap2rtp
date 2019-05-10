package com.xp.jrtp.voip;

import java.util.Objects;

public class Phone {
    private String from;
    private String to;
    private String ssrc;
    private int pt;
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSsrc() {
        return ssrc;
    }

    public void setSsrc(String ssrc) {
        this.ssrc = ssrc;
    }

    public int getPt() {
        return pt;
    }

    public void setPt(int pt) {
        this.pt = pt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;

        return Objects.equals(from, phone.from) &&
                Objects.equals(to, phone.to) ||
                Objects.equals(from, phone.to) &&
                        Objects.equals(to, phone.from);
    }

    @Override
    public int hashCode() {
        return 25;
    }
}

package com.xp.jrtp.voip;

import com.xp.jrtp.tool.TextTool;

import java.util.Objects;

public class Key {
    private String branch;
    private String sipSrcIp;
    private int sipSrcPort;
    private String sipDesIp;
    private int sipDesPort;

    private String ssrc;
    private String rtpSrcIp;
    private int rtpSrcPort;
    private String rtpDesIp;
    private int rtpDesPort;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSipSrcIp() {
        return sipSrcIp;
    }

    public void setSipSrcIp(String sipSrcIp) {
        this.sipSrcIp = sipSrcIp;
    }

    public int getSipSrcPort() {
        return sipSrcPort;
    }

    public void setSipSrcPort(int sipSrcPort) {
        this.sipSrcPort = sipSrcPort;
    }

    public String getSipDesIp() {
        return sipDesIp;
    }

    public void setSipDesIp(String sipDesIp) {
        this.sipDesIp = sipDesIp;
    }

    public int getSipDesPort() {
        return sipDesPort;
    }

    public void setSipDesPort(int sipDesPort) {
        this.sipDesPort = sipDesPort;
    }

    public String getSsrc() {
        return ssrc;
    }

    public void setSsrc(String ssrc) {
        this.ssrc = ssrc;
    }

    public String getRtpSrcIp() {
        return rtpSrcIp;
    }

    public void setRtpSrcIp(String rtpSrcIp) {
        this.rtpSrcIp = rtpSrcIp;
    }

    public int getRtpSrcPort() {
        return rtpSrcPort;
    }

    public void setRtpSrcPort(int rtpSrcPort) {
        this.rtpSrcPort = rtpSrcPort;
    }

    public String getRtpDesIp() {
        return rtpDesIp;
    }

    public void setRtpDesIp(String rtpDesIp) {
        this.rtpDesIp = rtpDesIp;
    }

    public int getRtpDesPort() {
        return rtpDesPort;
    }

    public void setRtpDesPort(int rtpDesPort) {
        this.rtpDesPort = rtpDesPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        if (branch != null && Objects.equals(branch, key.branch) ||
                ssrc != null && Objects.equals(ssrc, key.ssrc)) {
            return true;
        }
        if (Objects.equals(sipDesIp, key.rtpSrcIp) &&
                Objects.equals(sipDesPort, key.rtpSrcPort) ||
                Objects.equals(sipDesIp, key.rtpDesIp) &&
                        Objects.equals(sipDesPort, key.rtpDesPort)) {
            rtpSrcIp = key.rtpSrcIp;
            rtpDesIp = key.rtpDesIp;
            rtpSrcPort = key.rtpSrcPort;
            rtpDesPort = key.rtpDesPort;
            ssrc = key.ssrc;
            return true;
        }
        if (Objects.equals(rtpSrcIp, key.sipDesIp) &&
                Objects.equals(rtpSrcPort, key.sipDesPort) ||
                Objects.equals(rtpDesIp, key.sipDesIp) &&
                        Objects.equals(rtpDesPort, key.sipDesPort)) {
            sipSrcIp = key.sipSrcIp;
            sipDesIp = key.sipDesIp;
            sipSrcPort = key.sipSrcPort;
            sipDesPort = key.sipDesPort;
            branch = key.branch;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 25;
    }
}

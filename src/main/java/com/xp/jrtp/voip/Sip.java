package com.xp.jrtp.voip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sip {
    private String from;
    private String to;
    //conn net type;
    private String cnType;
    //conn address type
    private String cdType;
    //conn address
    private String cd;
    //media type
    private String mType;
    //media port
    private String mPort;
    //media protocol
    private String mProtocol;
    //media format1
    private String mf1;
    //media format2
    private String mf2;
    private Phone phone;
    private String branch;

    public Phone getPhone() {
        if (phone == null) {
            phone = new Phone();
            phone.setFrom(from);
            phone.setTo(to);
        }
        return phone;
    }


    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }


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

    public String getCnType() {
        return cnType;
    }

    public void setCnType(String cnType) {
        this.cnType = cnType;
    }

    public String getCdType() {
        return cdType;
    }

    public void setCdType(String cdType) {
        this.cdType = cdType;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmPort() {
        return mPort;
    }

    public void setmPort(String mPort) {
        this.mPort = mPort;
    }

    public String getmProtocol() {
        return mProtocol;
    }

    public void setmProtocol(String mProtocol) {
        this.mProtocol = mProtocol;
    }

    public String getMf1() {
        return mf1;
    }

    public void setMf1(String mf1) {
        this.mf1 = mf1;
    }

    public String getMf2() {
        return mf2;
    }

    public void setMf2(String mf2) {
        this.mf2 = mf2;
    }


}

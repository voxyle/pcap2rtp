package com.xp.jrtp.header;


/**
 * IP 头
 * @author voxyle
 *
 */
public class IPHeader {

	/**
	 * 协议版本号(4 bit)及包头长度(4bit) =（1 字节）
	 * 版本号(Version):一般的值为0100（IPv4），0110（IPv6）
	 * IP包头最小长度为20字节
	 */
	private byte varHLen;
	private int version;
	private int hLength;
	/**
	 * Type of  Service：服务类型，（1 字节）
	 */
	private byte tos;

	/**
	 * 总长度（2 字节）
	 */
	private int totalLen;

	/**
	 * 标识（2 字节）
	 */
	private int id;
	
	/**
	 * 标志与偏移量（2 字节）
	 */
	private int flagSegment;
	
	/**
	 * Time to Live：生存周期（1 字节）
	 */
	private byte ttl;
	
	/**
	 * 协议类型（1 字节）
	 */
	private byte protocol;
	
	/**
	 * 头部校验和（2 字节）
	 */
	private int checkSum;
	
	/**
	 * 源 IP（4 字节）
	 */
	private String srcIP;
	
	/**
	 * 目的 IP（4 字节）
	 */
	private String dstIP;

	public byte getVarHLen() {
		return varHLen;
	}

	public void setVarHLen(byte varHLen) {
		this.varHLen = varHLen;
	}

	public byte getTos() {
		return tos;
	}

	public void setTos(byte tos) {
		this.tos = tos;
	}

	public int getTotalLen() {
		return totalLen;
	}

	public void setTotalLen(int totalLen) {
		this.totalLen = totalLen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlagSegment() {
		return flagSegment;
	}

	public void setFlagSegment(int flagSegment) {
		this.flagSegment = flagSegment;
	}

	public byte getTtl() {
		return ttl;
	}

	public void setTtl(byte ttl) {
		this.ttl = ttl;
	}

	public byte getProtocol() {
		return protocol;
	}

	public void setProtocol(byte protocol) {
		this.protocol = protocol;
	}

	public int getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(int checkSum) {
		this.checkSum = checkSum;
	}

	public String getSrcIP() {
		return srcIP;
	}

	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}

	public String getDstIP() {
		return dstIP;
	}

	public void setDstIP(String dstIP) {
		this.dstIP = dstIP;
	}

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int gethLength() {
        return hLength;
    }

    public void sethLength(int hLength) {
        this.hLength = hLength;
    }

    public IPHeader() {	}

}

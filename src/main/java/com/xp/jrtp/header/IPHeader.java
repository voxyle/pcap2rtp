package com.xp.jrtp.header;


/**
 * IP ���ݱ�ͷ
 * @author johnnie
 *
 */
public class IPHeader {

	/**
	 * Э��汾��(4 bit)����ͷ����(4bit) =��1 �ֽڣ�
	 * �汾��(Version):һ���ֵΪ0100��IPv4����0110��IPv6��
	 * IP��ͷ��С����Ϊ20�ֽ�
	 */
	private byte varHLen;
	private int version;
	private int hLength;
	/**
	 * Type of  Service���������ͣ���1 �ֽڣ�
	 */
	private byte tos;
	
	/**
	 * �ܳ��ȣ�2 �ֽڣ�
	 */
	private int totalLen;
	
	/**
	 * ��ʶ��2 �ֽڣ�
	 */
	private int id;
	
	/**
	 * ��־��ƫ������2 �ֽڣ�
	 */
	private int flagSegment;
	
	/**
	 * Time to Live���������ڣ�1 �ֽڣ�
	 */
	private byte ttl;
	
	/**
	 * Э�����ͣ�1 �ֽڣ�
	 */
	private byte protocol;
	
	/**
	 * ͷ��У��ͣ�2 �ֽڣ�
	 */
	private int checkSum;
	
	/**
	 * Դ IP��4 �ֽڣ�
	 */
	private String srcIP;
	
	/**
	 * Ŀ�� IP��4 �ֽڣ�
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

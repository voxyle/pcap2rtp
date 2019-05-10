package com.xp.jrtp.header;


/**
 * UDP 包头：由4个域组成，每个域各占用2个字节
 * @author voxyle
 *
 */
public class UDPHeader {
	//下面四个每一个都是两个字节
	private int srcPort;			// 源端口
	private int dstPort;			// 目的端口
	private int length;			// 数据包长(包括这4个域8个字节)
	private int checkSum;		// 校验和
	
	public int getSrcPort() {
		return srcPort;
	}
	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}
	public int getDstPort() {
		return dstPort;
	}
	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(int checkSum) {
		this.checkSum = checkSum;
	}


	
}

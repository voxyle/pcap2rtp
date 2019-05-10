package com.xp.jrtp.header;


/**
 * UDP ��ͷ����4������ɣ�ÿ�����ռ��2���ֽ�
 * @author johnnie
 *
 */
public class UDPHeader {
	//�����ĸ�ÿһ�����������ֽ�
	private int srcPort;			// Դ�˿�
	private int dstPort;			// Ŀ�Ķ˿�
	private int length;			// ���ݰ���
	private int checkSum;		// У���
	
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

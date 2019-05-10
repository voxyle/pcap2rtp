package com.xp.jrtp.header;

import com.xp.jrtp.type.ProtocolType;

/**
 * Э�����ݣ���Ԫ��
 * @author johnnie
 *
 */
public class ProtocolData {

	String srcIP;										// Դ IP
	String desIP;										// Ŀ�� IP

	int srcPort;										// Դ�˿�
	int desPort;										// Ŀ�Ķ˿�
	
	ProtocolType protocolType = ProtocolType.OTHER;		// Э������

	public String getSrcIP() {
		return srcIP;
	}

	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}

	public String getDesIP() {
		return desIP;
	}

	public void setDesIP(String desIP) {
		this.desIP = desIP;
	}

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public int getDesPort() {
		return desPort;
	}

	public void setDesPort(int desPort) {
		this.desPort = desPort;
	}

	public ProtocolType getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(ProtocolType protocolType) {
		this.protocolType = protocolType;
	}

	public ProtocolData() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "ProtocolData [srcIP=" + srcIP
				+ ", desIP=" + desIP
				+ ", srcPort=" + srcPort
				+ ", desPort=" + desPort
				+ ", protocolType=" + protocolType
				+ "]";
	}

}

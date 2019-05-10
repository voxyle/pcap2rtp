package com.xp.jrtp.header;

import com.xp.jrtp.type.ProtocolType;

/**
 * 协议数据，五元组
 * @author johnnie
 *
 */
public class ProtocolData {

	String srcIP;										// 源 IP
	String desIP;										// 目的 IP

	int srcPort;										// 源端口
	int desPort;										// 目的端口
	
	ProtocolType protocolType = ProtocolType.OTHER;		// 协议类型

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

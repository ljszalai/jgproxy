package com.comuv.szalai.proxy;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class TcpConnectionData {
	
	private String hostName;
	private int portNumber;
	
	public TcpConnectionData() {
		super();
		this.hostName = null;
		this.portNumber = -1;
	}
	
	public TcpConnectionData(int port) {
		super();
		this.portNumber = port;
	}
	
	public TcpConnectionData(String hostName, int port) {
		super();
		this.hostName = hostName;
		this.portNumber = port;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public InetAddress getHostInetAddress() throws UnknownHostException {
		return InetAddress.getByName(this.hostName);
	}
	
	public int getPort() {
		return portNumber;
	}
	
	public void setPort(int portNumber) {
		this.portNumber = portNumber;
	}
	
	public String getPortAsString() {
		return Integer.valueOf(portNumber).toString();
	}

}

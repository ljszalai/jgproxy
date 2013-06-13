package com.comuv.szalai.proxy.test;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.comuv.szalai.proxy.TcpConnectionData;

public class TcpConnectionDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTcpConnectionData() {
		TcpConnectionData tcpcd = new TcpConnectionData(); 
		assertEquals (null, tcpcd.getHostName());
		assertEquals (-1, tcpcd.getPort());
	}

	@Test
	public void testTcpConnectionDataInt() {
		TcpConnectionData tcpcd = new TcpConnectionData(125); 
		assertEquals (null, tcpcd.getHostName());
		assertEquals (125, tcpcd.getPort());
	}

	@Test
	public void testTcpConnectionDataStringInt() {
		TcpConnectionData tcpcd01 = new TcpConnectionData("nowhere.example.com", 12358); 
		assertEquals ("nowhere.example.com", tcpcd01.getHostName());
		assertEquals (12358, tcpcd01.getPort());
		TcpConnectionData tcpcd02 = new TcpConnectionData(null, 12358); 
		assertEquals (null, tcpcd02.getHostName());
		assertEquals (12358, tcpcd02.getPort());
	}

	@Test
	public void testGetHostName() {
		TcpConnectionData tcpcd01 = new TcpConnectionData("nowhere.example.com", 12358);
		assertEquals ("nowhere.example.com", tcpcd01.getHostName());
		TcpConnectionData tcpcd02 = new TcpConnectionData(null, 12358); 
		assertEquals (null, tcpcd02.getHostName());
	}

	@Test
	public void testSetHostName() {
		TcpConnectionData tcpcd01 = new TcpConnectionData("nowhere01.example.com", 12358);
		assertEquals ("nowhere01.example.com", tcpcd01.getHostName());
		tcpcd01.setHostName("nowhere02.example.com");
		assertEquals ("nowhere02.example.com", tcpcd01.getHostName());
		tcpcd01.setHostName(null);
		assertEquals (null, tcpcd01.getHostName());

		TcpConnectionData tcpcd = new TcpConnectionData();
		tcpcd.setHostName(null);
		assertEquals (null, tcpcd.getHostName());

	}

	@Test
	public void testGetHostInetAddress() {
		TcpConnectionData tcpcd01 = new TcpConnectionData("nonexistent.host.miert.hu", 12358);
		TcpConnectionData tcpcd02 = new TcpConnectionData(null, 12358);
		try {
			InetAddress ia1 = tcpcd01.getHostInetAddress();
			InetAddress ia2 = tcpcd02.getHostInetAddress();
			ia1.toString();
			ia2.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPort() {
		TcpConnectionData tcpcd01 = new TcpConnectionData("nowhere.example.com", 12358);
		assertEquals (12358, tcpcd01.getPort());
		TcpConnectionData tcpcd02 = new TcpConnectionData(null, 1248); 
		assertEquals (1248, tcpcd02.getPort());
	}

	@Test
	public void testSetPort() {
		TcpConnectionData tcpcd01 = new TcpConnectionData("nowhere.example.com", 12358);
		assertEquals (12358, tcpcd01.getPort());
		tcpcd01.setPort(124816);
		assertEquals (124816, tcpcd01.getPort());
	}

	@Test
	public void testGetPortAsString() {
		TcpConnectionData tcpcd01 = new TcpConnectionData("nowhere.example.com", 12358);
		assertEquals ("12358", tcpcd01.getPortAsString());
		tcpcd01.setPort(124816);
		assertEquals ("124816", tcpcd01.getPortAsString());
	}

}

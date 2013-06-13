/*
Copyright 2013 Laszlo Szalai

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
/*
 * Tcp Proxy
 * 
 * Original author: Daniel W. Goldberg
 * Original source downloaded from 
 *     http://www.dwgold.com/Projects/Networking/Proxyserver/Default.aspx
 *      
 */

package com.comuv.szalai.proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.comuv.szalai.proxy.interfaces.IStreamTamperer;
import com.comuv.szalai.proxy.interfaces.IStreamTapper;

public class JGProxy {

	protected static final int backLog = 64;
	
	private boolean b_run;
	private IStreamTapper tapper;
	private IStreamTamperer tamperer;

	private TcpConnectionData nearEnd;
	private TcpConnectionData farEnd;

	private Socket incoming;
	private Socket outgoing;
	private ServerSocket Server;

	public JGProxy(String localHost, int localPort, String remoteHost, int remotePort) {
		initDefaults();
		initConnData(localHost, localPort, remoteHost, remotePort);
	}

	public JGProxy(TcpConnectionData nearEnd, TcpConnectionData farEnd) {
		initDefaults();
		initConnData(nearEnd.getHostName(), nearEnd.getPort(), farEnd.getHostName(), farEnd.getPort());
	}

	public JGProxy() {
		initDefaults();
		initConnData(null, -1, null, -1);	
	}

	private void initDefaults() {
		b_run = true;
		tapper = null;
		tamperer = null;
		incoming  = null;
		outgoing = null;
		Server = null;
	}

	private void initConnData(String localHost, int localPort, String remoteHost, int remotePort) {
		nearEnd = new TcpConnectionData(localHost, localPort);
		farEnd = new TcpConnectionData(remoteHost, remotePort);
	}

	public IStreamTapper getTapper() {
		return tapper;
	}

	public void setTapper(IStreamTapper tapper) {
		this.tapper = tapper;
	}

	public IStreamTamperer getTamperer() {
		return tamperer;
	}

	public void setTamperer(IStreamTamperer tamperer) {
		this.tamperer = tamperer;
	}

	public void stopProxy() {
		this.b_run = false;
	}

	public void runProxy() {
		//Test and create a listening socket at proxy

		try{
			Server = 
					(nearEnd.getHostName()==null) ? 
							new ServerSocket(nearEnd.getPort(), backLog) : 
							new ServerSocket(nearEnd.getPort(), backLog, nearEnd.getHostInetAddress());
		}
		catch(IOException e) {
			System.err.println("Error: Unknown Host " + nearEnd.getHostName());
			System.exit(-1);
		}

		//Loop to listen for incoming connection, and accept if there is one

		//Third problem: why don't we use a thread instead of this endless loop?

		while(b_run)
		{
			try{
				ProxyThread thread1;
				ProxyThread thread2;

				incoming = Server.accept();
				outgoing = new Socket(farEnd.getHostName(), farEnd.getPort()); 

				if (tapper == null) {
					thread1 = new ProxyThread(incoming, outgoing);
					thread2 = new ProxyThread(outgoing, incoming);
				} else {
					thread1 = new ProxyThread(incoming, outgoing, tapper);
					thread2 = new ProxyThread(outgoing, incoming, tapper);

				}
				thread1.start();
				thread2.start();
			} 
			catch (UnknownHostException e) {
				System.err.println("Error: Unknown Host " + farEnd.getHostName());
				System.exit(-1);
			} 
			catch(IOException e){
				System.exit(-2);//continue;
			}

		}
	}


}

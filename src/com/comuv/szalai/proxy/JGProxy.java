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

public class JGProxy {
	
	private boolean b_run = true;
	
	public void stopProxy() {
		this.b_run = false;
	}

	public void runProxy(String args[]) throws IOException{

		//parse arguments from command line

		int localport = -1;
		int remoteport = -1;
		String remotehost = null;
		boolean error = false;

		int i = 0;
		// First bug identified: index out of bounds :-)
		Integer parselocalport = new Integer(args[i]);
		Integer parseremoteport = new Integer(args[i+2]);
		Socket incoming, outgoing = null;
		ServerSocket Server = null;

		try
		{
			localport = parselocalport.parseInt(args[i], 10);
			remotehost = args[i+1];
			remoteport = parseremoteport.parseInt(args[i+2], 10);
		}

		catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage() + "\n");
			error = true;
		}

		// Check for valid local and remote port, hostname not null
		// Second bug: no space in text :-)
		System.out.println("Checking: Port" + localport + " to " + remotehost + " Port " + remoteport);

		if(localport <= 0){
			System.err.println("Error: Invalid Local Port Specification " + "\n");
			error = true;
		}
		if(remoteport <=0){
			System.err.println("Error: Invalid Remote Port Specification " + "\n");
			error = true;
		}
		if(remotehost == null){
			System.err.println("Error: Invalid Remote Host Specification " + "\n");
			error = true;
		}

		//If any errors so far, exit program

		if(error)
			System.exit(-1);


		//Test and create a listening socket at proxy

		try{
			Server = new ServerSocket(localport);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		//Loop to listen for incoming connection, and accept if there is one
		
		//Third problem: why don't we use a thread instead of this endless loop?

		while(b_run)
		{
			try{
				incoming = Server.accept();
				//Create the 2 threads for the incoming and outgoing traffic of proxy server
				outgoing = new Socket(remotehost, remoteport); 

				ProxyThread thread1 = new ProxyThread(incoming, outgoing);
				thread1.start();

				ProxyThread thread2 = new ProxyThread(outgoing, incoming);
				thread2.start();
			} 
			catch (UnknownHostException e) {
				//Test and make connection to remote host
				System.err.println("Error: Unknown Host " + remotehost);
				System.exit(-1);
			} 
			catch(IOException e){
				System.exit(-2);//continue;
			}

		}
	}


}















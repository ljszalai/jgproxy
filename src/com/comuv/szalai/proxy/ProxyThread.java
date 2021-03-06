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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.comuv.szalai.proxy.interfaces.IStreamMonitor;
import com.comuv.szalai.proxy.manipulators.ConsoleStreamMonitor;

class ProxyThread extends Thread {

	public static final int bufSize = 2048;
	
	private Socket incoming;
	private Socket outgoing;
	private IStreamMonitor streamMonitor;
	

	//Thread constructor

	// TODO value checking
	ProxyThread(Socket in, Socket out){
		incoming = in;
		outgoing = out;
		streamMonitor = new ConsoleStreamMonitor();
	}

	// TODO value checking
	ProxyThread(Socket in, Socket out, IStreamMonitor stMonitor){
		incoming = in;
		outgoing = out;
		streamMonitor = stMonitor;
	}

	//Overwritten run() method of thread -- does the data transfers

	public void run(){
		byte[] buffer = new byte[bufSize];
		int numberRead = 0;
		OutputStream ToClient;
		InputStream FromClient;

		try{
			ToClient = outgoing.getOutputStream();      
			FromClient = incoming.getInputStream();
			while(((numberRead=FromClient.read(buffer))>0)&&!isInterrupted()){

				if(numberRead == -1){
					incoming.close();
					outgoing.close();
				}
				// TODO monitoring & interception goes here ...
				monitorBuffer(buffer, numberRead);
				ToClient.write(buffer, 0, numberRead);


			}

		}
		catch(IOException e) {
			// TODO
		}
		catch(ArrayIndexOutOfBoundsException e) {
			// TODO
		}

	}
	
	private void monitorBuffer(byte[] buffer, int count) {
		if (streamMonitor != null)
			streamMonitor.doMonitor(new StreamSlice(buffer, count));
	}

}

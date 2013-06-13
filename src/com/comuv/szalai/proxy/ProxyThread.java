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
/*

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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ProxyThread extends Thread {

	// Size of receive buffer
	public static final int bufSize = 2048;
	
Socket incoming, outgoing;

	//Thread constructor

	ProxyThread(Socket in, Socket out){
		incoming = in;
		outgoing = out;
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
				
				System.out.println("read " + numberRead);

				if(numberRead == -1){
					incoming.close();
					outgoing.close();
				}
				// TODO Tap & tamper goes here ...
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

}

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

package com.comuv.szalai.proxy.runners;

import com.comuv.szalai.proxy.JGProxy;
import com.comuv.szalai.proxy.TcpConnectionData;

public class ProxyRunner {
	
	private static boolean error = true;
	private static TcpConnectionData nearEnd = null;
	private static TcpConnectionData farEnd = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		parseArgs(args);
		
		if(error)
			System.exit(-1);
		
		JGProxy proxy = new JGProxy(nearEnd, farEnd);
		proxy.runProxy();

	}
	
	private static void parseArgs(String args[]) {
		try
		{
			nearEnd = new TcpConnectionData(Integer.parseInt(args[0], 10));
			farEnd = new TcpConnectionData(args[1], Integer.parseInt(args[2], 10));
			error = false;
		} catch(Exception e) {
			System.err.println("Error: " + e.getMessage() + "\n");
			error = true;
		}

		System.out.println("Checking: Port " + nearEnd.getPort() + " to " + farEnd.getHostName() + " Port " + farEnd.getPort());

		if(nearEnd.getPort() <= 0) {
			System.err.println("Error: Invalid Local Port Specification " + "\n");
			error = true;
		}
		if(farEnd.getPort() <=0) {
			System.err.println("Error: Invalid Remote Port Specification " + "\n");
			error = true;
		}
		if(farEnd.getHostName() == null) {
			System.err.println("Error: Invalid Remote Host Specification " + "\n");
			error = true;
		}
	}



}

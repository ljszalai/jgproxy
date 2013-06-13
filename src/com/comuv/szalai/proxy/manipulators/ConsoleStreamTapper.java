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

package com.comuv.szalai.proxy.manipulators;

import com.comuv.szalai.proxy.HexDump;
import com.comuv.szalai.proxy.StreamSlice;
import com.comuv.szalai.proxy.interfaces.IStreamTapper;

public class ConsoleStreamTapper implements IStreamTapper {

	@Override
	public void Tap(StreamSlice streamSlice) {
		System.out.println(HexDump.dumpHexString(streamSlice.getBytes(), 0, streamSlice.getCount()));
	}

}

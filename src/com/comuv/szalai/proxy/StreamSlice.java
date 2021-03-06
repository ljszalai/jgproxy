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

package com.comuv.szalai.proxy;

import java.nio.ByteBuffer;

public class StreamSlice {
	
	private byte[] bytes;
	private int count;
	
	public StreamSlice(byte[] bytes, int count) {
		super();
		this.bytes = bytes;
		this.count = count;
	}

	public StreamSlice(StreamSlice streamSlice) {
		super();
		this.bytes = streamSlice.getBytes();
		this.count = streamSlice.getCount();
	}

	public StreamSlice(ByteBuffer buf) {
		super();
		this.bytes = new byte[buf.remaining()];
		this.count = bytes.length;
	}

	public StreamSlice() {
		super();
		this.bytes = null;
		this.count = -1;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
		this.count = bytes.length;
	}

	public int getCount() {
		return count;
	}

	public ByteBuffer getAsByteBuffer() {
		ByteBuffer buf = ByteBuffer.wrap(bytes);
		buf.put(bytes);
		return buf;
	}
	
}

package com.comuv.szalai.proxy.test;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

import com.comuv.szalai.proxy.StreamSlice;

public class StreamSliceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testStreamSliceByteArrayInt() {
		byte[] testData = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67 
		};
		StreamSlice stsl = new StreamSlice(testData, testData.length);
		assertNotNull (stsl);
		assertEquals (15, stsl.getCount());
	}

	@Test
	public void testStreamSliceStreamSlice() {
		byte[] testData = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67 
		};
		StreamSlice stsl01 = new StreamSlice(testData, testData.length);
		assertNotNull (stsl01);
		assertEquals (15, stsl01.getCount());
		StreamSlice stsl02 = new StreamSlice(stsl01);
		assertNotNull (stsl02);
		assertEquals (15, stsl02.getCount());
	}

	@Test
	public void testStreamSliceByteBuffer() {
		byte[] testData = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67 
		};
		ByteBuffer tdByteBuffer = ByteBuffer.wrap(testData);
		StreamSlice stsl01 = new StreamSlice(tdByteBuffer);
		assertNotNull (stsl01);
		assertEquals (15, stsl01.getCount());
	}

	@Test
	public void testStreamSlice() {
		StreamSlice stsl01 = new StreamSlice();
		assertNotNull (stsl01);
		assertEquals (-1, stsl01.getCount());
		assertEquals (null, stsl01.getBytes());
	}

	@Test
	public void testGetBytes() {
		byte[] testData = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67 
		};
		StreamSlice stsl01 = new StreamSlice(testData, testData.length);
		assertNotNull (stsl01);
		assertArrayEquals (testData, stsl01.getBytes());
	}

	@Test
	public void testSetBytes() {
		byte[] testData01 = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67 
		};
		byte[] testData02 = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67, 74 
		};
		StreamSlice stsl01 = new StreamSlice(testData01, testData01.length);
		assertNotNull (stsl01);
		assertArrayEquals (testData01, stsl01.getBytes());
		stsl01.setBytes(testData02);
		assertArrayEquals (testData02, stsl01.getBytes());
		assertEquals (16, stsl01.getCount());
	}

	@Test
	public void testGetAsByteBuffer() {
		byte[] testData01 = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67 
		};
		byte[] testData02 = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 48, 33, 34, 35, 36, 20, 65, 66, 67, 74 
		};
		StreamSlice stsl01 = new StreamSlice(testData01, testData01.length);
		assertNotNull (stsl01);
		ByteBuffer expected01 = ByteBuffer.wrap(testData01);
		expected01.flip();
		assertEquals (expected01, stsl01.getAsByteBuffer());
		stsl01.setBytes(testData02);
		ByteBuffer expected02 = ByteBuffer.wrap(testData02);
		expected02.flip();
		assertEquals (expected02, stsl01.getAsByteBuffer());
		assertEquals (16, stsl01.getCount());
	}

}

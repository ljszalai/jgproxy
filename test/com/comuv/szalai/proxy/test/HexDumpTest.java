/*
 * Copyright 2013 Laszlo Szalai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * The original source of HexDump code has been forked from
 * https://code.google.com/p/google-tv-pairing-protocol/source/browse/java/src/com/google/polo/pairing/HexDump.java
 * 
 */

package com.comuv.szalai.proxy.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.comuv.szalai.proxy.HexDump;


public class HexDumpTest {

	private byte[] testData;
	@Before
	public void setUp() throws Exception {
		testData = new byte[] {
			/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
				1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67 
		};
	}

	@Test
	public void testDumpHexStringByteArray() {
		assertEquals ("\n" + 
				"0x00000000 01 02 03 04 05 1F 20 21 22 23 24 40 41 42 43    .......!\"#$@ABC"
				,
				HexDump.dumpHexString(testData));
		testData = new byte[] {
				/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
					1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67,
					1, 2, 3, 4, 5,  6,  7,  8,  9, 97, 11, 12, 13, 14, 15
			};
		assertEquals ("\n" + 
				"0x00000000 01 02 03 04 05 1F 20 21 22 23 24 40 41 42 43 01 .......!\"#$@ABC." + "\n" +
				"0x00000010 02 03 04 05 06 07 08 09 61 0B 0C 0D 0E 0F       ........a....."
				,
				HexDump.dumpHexString(testData));
	}

	@Test
	public void testDumpHexStringByteArrayIntInt() {
		assertEquals ("\n" + 
				"0x00000004 05 1F 20 21 22 23 24 40                         ...!\"#$@"
				,
				HexDump.dumpHexString(testData, 4, 8));
	}

	@Test
	public void testToHexStringByte() {
		assertEquals ( 
				"41"
				,
				HexDump.toHexString((byte) 65));
		assertEquals ( 
				"00000041"
				,
				HexDump.toHexString(65));
		assertEquals ( 
				"61"
				,
				HexDump.toHexString((byte) 97));
		assertEquals ( 
				"00000061"
				,
				HexDump.toHexString(97));
		assertEquals ( 
				"14711E21"
				,
				HexDump.toHexString(342957601));
	}

	@Test
	public void testToHexStringByteArray() {
		assertEquals( 
				"01020304051F202122232440414243"
				,
				HexDump.toHexString(testData));
		
	}

	@Test
	public void testToHexStringByteArrayIntInt() {
		assertEquals( 
				"051F202122232440"
				,
				HexDump.toHexString(testData, 4, 8));
	}

	@Test
	public void testToSpacedHexStringByteArrayIntInt() {
		assertEquals( 
				"05 1F 20 21 22 23 24 40"
				,
				HexDump.toSpacedHexString(testData, 4, 8));
		assertEquals( 
				"01 02 03 04 05 1F 20 21 22 23 24 40 41 42 43"
				,
				HexDump.toSpacedHexString(testData, 0, testData.length));
	}

	@Test
	public void testToHexStringInt() {
		assertEquals( 
				"00000004"
				,
				HexDump.toHexString(4));
		assertEquals( 
				"26B6C6BB"
				,
				HexDump.toHexString(649512635));
	}

	@Test
	public void testToByteArrayByte() {
		assertArrayEquals( 
				new byte[] { 0, 0, 0, 32 }
				,
				HexDump.toByteArray(32));
		assertArrayEquals( 
				new byte[] { 38, -74, -58, -69 }
				,
				HexDump.toByteArray(649512635));
	}

	@Test
	public void testToByteArrayInt() {
		assertArrayEquals( 
				new byte[] { 32 }
				,
				HexDump.toByteArray((byte) 32));
		assertArrayEquals( 
				new byte[] { -127 }
				,
				HexDump.toByteArray((byte) 129));
	}

	@Test
	public void testHexStringToByteArray() {
		assertArrayEquals( 
				new byte[] {
						/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
							1, 35, 69, 104, 121, -85, -51, -17, 1, 35, 69, 104, 121, -85, -51, -17
					}
				,
				HexDump.hexStringToByteArray("0123456879ABCDEF0123456879ABCDEF"));
	}

	@Test(expected=RuntimeException.class)
	public void testHexStringToByteArray_Exception() {
		assertArrayEquals( 
				new byte[] {
						/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
							1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67,  1 
					}
				,
				HexDump.hexStringToByteArray("01020304051F202122232440414243g1"));
	}

	@Test
	public void testSpacedHexStringByteArray() {
		assertArrayEquals( 
				new byte[] {
						/*	1, 2, 3, 4, 5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16*/
							1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 36, 64, 65, 66, 67,  1 
					}
				,
				HexDump.spacedHexStringToByteArray("01 0203 04 05 1F20 2122 23 2440 41 42 43 01"));
	}

}

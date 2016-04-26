package com.sand.sign.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UTIL {
	public static final String FILLWITHSPACE = "fillwithspace";
	public static final String FILLWITHNULL = "fillwithnull";
	private static final char bcdLookup[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public UTIL() {

	}

	/**
	 * 截取出byte[]数组的一部分
	 * 
	 * @param bs
	 * @param first
	 * @param length
	 * @return byte[]
	 */
	public static byte[] sub(byte[] bs, int first, int length) {
		length = (first + length) > bs.length ? bs.length - first : length;
		byte[] nb = new byte[length];
		for (int i = 0; i < length; i++)
			nb[i] = bs[i + first];
		return nb;
	}

	/**
	 * bytesToHexStr 通过查数组方式快速获得byte串的HEX数据
	 * */
	public static final String bytesToHexStr(byte bcd[]) {
		StringBuffer s = new StringBuffer(bcd.length * 2);
		for (int i = 0; i < bcd.length; i++) {
			s.append(bcdLookup[bcd[i] >>> 4 & 0xf]);
			s.append(bcdLookup[bcd[i] & 0xf]);
		}
		return s.toString();
	}

	public static final String bytesToHexStr(byte bcd) {
		StringBuffer s = new StringBuffer(2);
		s.append(bcdLookup[bcd >>> 4 & 0xf]);
		s.append(bcdLookup[bcd & 0xf]);
		return s.toString();
	}

	/**
	 * BCD->ASCII原理：
	 * <p>
	 * 拆分byte的前4位和后4位，然后将这两个4位前面补上0x30(ascii中0123456789:; <=>的共同前4位) 组成两个新的byte.
	 * <p>
	 * 
	 * @param bs
	 *            待转换的bcd字节数组。
	 * @return 转换为ascii后的字节数组。
	 */
	public static final byte[] bcd2ascii(byte[] bs) {
		byte[] res = new byte[bs.length * 2];
		for (int i = 0, n = bs.length; i < n; i++) {
			res[i * 2] = (byte) (((bs[i] & 0xf0) >> 4) | 0x30);
			res[i * 2 + 1] = (byte) ((bs[i] & 0x0f) | 0x30);
		}
		return res;
	}

	public static final int bcd2int(byte[] data) {
		return Integer.parseInt(new String(bcd2ascii(data)));
	}

	public static final int bcd2int(byte[] data, int offset, int len) {
		byte[] body = new byte[len];
		System.arraycopy(data, offset, body, 0, len);
		return bcd2int(body);
	}

	/**
	 * uniteBytes 将两个字节压缩为一个字节
	 * */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode(
				(new StringBuilder("0x")).append(
						new String(new byte[] { src0 })).toString())
				.byteValue();
		_b0 <<= 4;
		byte _b1 = Byte.decode(
				(new StringBuilder("0x")).append(
						new String(new byte[] { src1 })).toString())
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * HexString2Bytes 将String编码压缩为Byte编码
	 * */
	public static byte[] HexString2Bytes(String src) {
		src = src.toUpperCase();
		int length = src.length() / 2;
		byte ret[] = new byte[length];
		byte tmp[] = src.getBytes();
		for (int i = 0; i < length; i++)
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		return ret;
	}

	/**
	 * 合并两个byte数组。合并后的数组将bs1放在在bs2的前面。
	 * 
	 * @param bs1
	 * @param bs2
	 * @return byte[]
	 */
	public static byte[] union(byte[] bs1, byte[] bs2) {
		byte[] bs = new byte[bs1.length + bs2.length];
		for (int i = 0; i < bs1.length; i++)
			bs[i] = bs1[i];
		for (int i = 0; i < bs2.length; i++)
			bs[bs1.length + i] = bs2[i];
		return bs;
	}

	/**
	 * FillArray 将byte数组以0x00填充为8的倍数长度
	 * */
	public static byte[] FillArray(byte[] data) {
		int nn = 8 - data.length % 8;
		if (nn != 8) {
			byte[] tbs = new byte[nn];
			for (int i = 0; i < nn; i++)
				tbs[i] = 0x00;
			return (union(data, tbs));
		} else {
			return data;
		}
	}

	/**
	 * FillArray 将byte数组以mode指定的方式填充为8的倍数长度
	 * */
	public static byte[] FillArray(byte[] data, String mode) {
		int nn = 8 - data.length % 8;
		if (nn != 8) {
			byte[] tbs = new byte[nn];
			for (int i = 0; i < nn; i++) {
				if (mode.equals(FILLWITHSPACE))
					tbs[i] = 0x20;
				if (mode.equals(FILLWITHNULL))
					tbs[i] = 0x00;
			}
			return (union(data, tbs));
		} else {
			return data;
		}
	}

	/**
	 * 字符集转换
	 * */
	public static String iso8859togbk(String strvalue) {
		if (strvalue == null)
			return null;
		try {
			strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
			return strvalue;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String gbkto8859(String strvalue) {
		if (strvalue == null)
			return null;
		try {
			strvalue = new String(strvalue.getBytes("gbk"), "ISO8859_1");
			return strvalue;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * short2bytes
	 * */
	public static byte[] short2bytes(short s) {
		byte bytes[] = new byte[2];
		for (int i = 1; i >= 0; i--) {
			bytes[i] = (byte) (s % 256);
			s >>= 8;
		}
		return bytes;
	}

	/**
	 * bytes2short
	 * */
	public static short bytes2short(byte bytes[]) {
		short s = (short) (bytes[1] & 0xff);
		s |= bytes[0] << 8 & 0xff00;
		return s;
	}

	/**
	 * PrintHexData 16字节一组输出hex数据
	 * */
	public static String PrintHexData(byte bytes[]) {
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			content.append("[").append(bytesToHexStr(bytes[i])).append("]");
			if ((i + 1) % 16 == 0)
				content.append("\n");
		}
		System.out.println(content.toString());
		return content.toString();
	}

	public static String PrintHexData(String msg, byte bytes[]) {
		StringBuffer content = new StringBuffer();
		System.out.print(msg);
		for (int i = 0; i < bytes.length; i++) {
			content.append("[").append(bytesToHexStr(bytes[i])).append("]");
			if ((i + 1) % 16 == 0)
				content.append("\n");
		}
		System.out.println(content.toString());
		return content.toString();
	}

	public static String PrintHexData(String msg, byte bytes[], int offset) {
		StringBuffer content = new StringBuffer();
		System.out.print(msg);
		for (int i = offset; i < bytes.length; i++) {
			content.append("[").append(bytesToHexStr(bytes[i])).append("]");
			if ((i + 1) % 16 == 0)
				content.append("\n");
		}
		System.out.println(content.toString());
		return content.toString();
	}

	/**
	 * 
	 * @param date
	 *            日期
	 * @param patteran
	 *            格式化日期输出
	 * @return
	 */
	public static String getDateString(Date date, String pattern) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}

	/**
	 * 字符串转对应日期格式
	 * 
	 * @param dateStr
	 *            字符串日期
	 * @param pattern
	 *            yyyyMMddHHmmss
	 * @return
	 */
	public static Date getDate(String dateStr, String pattern) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		try {
			return sf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * BCD码转换为ASICC字符串
	 * */
	public static String bcd2str(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			int h = ((b[i] & 0xff) >> 4) + 48;
			sb.append((char) h);
			int l = (b[i] & 0x0f) + 48;
			sb.append((char) l);
		}
		return sb.toString();
	}

	/**
	 * ASICC码转换为BCD码,往前补0
	 * */
	public static byte[] str2bcd(String s) {
		if (s.length() % 2 != 0) {
			s = "0" + s;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i += 2) {
			int high = cs[i] - 48;
			int low = cs[i + 1] - 48;
			baos.write(high << 4 | low);
		}
		return baos.toByteArray();
	}

	/**
	 * ASICC码转换为BCD码,往后补0
	 * */
	public static byte[] str2bcd2(String s) {
		if (s.length() % 2 != 0) {
			s = s + "0";
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i += 2) {
			int high = cs[i] - 48;
			int low = cs[i + 1] - 48;
			baos.write(high << 4 | low);
		}
		return baos.toByteArray();
	}

	/**
	 * FormatLen将INT值格式化为约定长度的ASICC串
	 * */
	public static String FormatLen(int v, int l) {
		String v1 = String.valueOf(v);
		if (v1.length() >= l)
			return v1;
		StringBuilder sb = new StringBuilder();
		for (int i = v1.length(); i < l; i++) {
			sb.append("0");
		}
		sb.append(v1);
		return sb.toString();
	}

	/**
	 * roll获得一个整型随机数
	 * */
	public static int roll() {
		double a, b, c;
		a = Math.random();
		b = Math.random();
		c = Math.random();
		return (int) (a * 100 * b / c);
	}

	/**
	 * 获得一个0-mode的随机数
	 * */
	public static int roll(int mode) {
		double a, b, c;
		a = Math.random();
		b = Math.random();
		c = Math.random();
		return (int) (a * 100 * b / c) % mode;
	}

	public static String GetRandom(int length) {
		String random = "";
		for (int i = 0; i < length; i++) {
			random = random + bcdLookup[roll(16)];
		}
		return random;
	}

	public static String GetRandom(int length, int mode) {
		String random = "";
		if (mode >= 16 | mode <= 0)
			mode = 10;
		for (int i = 0; i < length; i++) {
			random = random + bcdLookup[roll(mode)];
		}
		return random;
	}

	/**
	 * GetHexData 将byte数组转换为ASCII数组 0x11 0x12 --> 11 12
	 * */
	public static String GetHexData(byte bytes[]) {
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
			content.append(bytesToHexStr(bytes[i]));
		return "";
	}

	/**
	 * GetHexData 获取bytes数组偏移量offset后的len长度的hex 0xab -->ab的值
	 * */
	public static String GetHexData(byte bytes[], int offset, int len) {
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < len; i++) {
			content.append(bytesToHexStr(bytes[offset + i]));
		}
		return content.toString();
	}

	/**
	 * PrintHexData 输出长度为len的数据的hex码
	 * */
	public static String PrintHexData(byte bytes[], int len) {
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < len; i++) {
			content.append("[").append(bytesToHexStr(bytes[i])).append("]");
		}
		System.out.println(content.toString());
		return content.toString();
	}

	/**
	 * 
	 * @param length
	 *            长度值
	 * @param size
	 *            转化为字符串后的长度位
	 * @return 长度的字符表示形式 中间添加空格
	 */
	public static String toLenStr(int length, int size) {
		String lenStr = "" + length;
		for (int i = lenStr.length(); i < size; i++) {
			if ((i % 2) == 0) {
				lenStr = "0" + lenStr;
			} else {
				lenStr = "0" + lenStr;
			}

		}
		return lenStr;
	}

	/**
	 * add by yingsonghao 2014-2-7 将map集合按键的升序排序并拼接
	 */
	public static String backData(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		List<String> keylis = new ArrayList<String>();
		Set<String> keyset = map.keySet();
		Iterator<String> keyit = keyset.iterator();
		while (keyit.hasNext()) {
			String key = keyit.next();
			/*
			 * if(!(key.equals("sign")) && !(key.equals("sign_type")) &&
			 * !(key.equals("is_success"))){ keylis.add(key); }
			 */
			keylis.add(key);
		}
		if (keylis.size() > 0) {
			Collections.sort(keylis);
			for (int i = 0; i < keylis.size(); i++) {
				if (i != 0) {
					sb.append("&");
				}
				sb.append(keylis.get(i));
				sb.append("=");
				if (keylis.get(i).equals("coupon_list")) {
					sb.append("<coupon_list/>");
				} else {
					sb.append(map.get(keylis.get(i)));
				}

			}
		}
		System.out.println("返回码验签：" + sb.toString());
		return sb.toString();
	}
}

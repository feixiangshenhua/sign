package com.sand.sign.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Sign {

	public static void main(String[] args) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			String a = "aaaadsaaaaaaaaaaaaaaaaaaaaaaaaaaaaadffffffffffffffffff";
			String b = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG2U45qFpFvKpW+7tZFDzSmSh60XqV6bOSyv3d" +
"NDVmz6vBXWcxSEnrdheDqpgOZcVV/hhwWBVlWoEpjfx5cGrLB8vMZDcvUUz6EKyqDkA+Kf5QecS/" +
"nEJ7xABiw8rTyTySrVJ5eIcDh4O6LzeVo0pS8RzDOQwf2w7MsSRVi/QG4QIDAQAB";
			System.out.println(b.length());
			// ±àÂë³É×Ö·û´®
			BASE64Encoder encoder = new BASE64Encoder();
			System.out.println(encoder.encode(digest.digest(a.getBytes())));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}

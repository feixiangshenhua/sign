package com.sand.test;

public class TestString {
	public static void main(String[] args) {
		String msg = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG2U45qFpFvKpW+7tZFDzSmSh60XqV6bOSyv3dNDVmz6vBXWcxSEnrdheDqpgOZcVV/hhwWBVlWoEpjfx5cGrLB8vMZDcvUUz6EKyqDkA+Kf5QecS/nEJ7xABiw8rTyTySrVJ5eIcDh4O6LzeVo0pS8RzDOQwf2w7MsSRVi/QG4QIDAQAB";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG2U45qFpFvKpW+7tZFDzSmSh60XqV6bOSyv3d" +
				"NDVmz6vBXWcxSEnrdheDqpgOZcVV/hhwWBVlWoEpjfx5cGrLB8vMZDcvUUz6EKyqDkA+Kf5QecS/"+
				"nEJ7xABiw8rTyTySrVJ5eIcDh4O6LzeVo0pS8RzDOQwf2w7MsSRVi/QG4QIDAQAB";
		System.out.println(msg.equals(publicKey));
	}
}

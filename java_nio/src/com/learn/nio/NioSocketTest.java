package com.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioSocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			socketChannel.connect(new InetSocketAddress("www.cnblogs.com", 80));
			ByteBuffer buffer = ByteBuffer.allocate(48);
			int bytesRead = socketChannel.read(buffer);
			while(bytesRead != -1) {
				buffer.flip();
				System.out.println(new String(buffer.array(),"utf8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

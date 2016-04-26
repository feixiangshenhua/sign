package com.learn.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * channel测试
 * 
 * @author xiaoyun 2015-11-18
 *
 */
public class FileChannelTest {
	public static void main(String[] args) throws IOException {
		testChannel();		
		//testScattering();
		//transForm();
	}
	
	/**
	 * 基本的 Channel 示例
	 * 使用FileChannel读取数据到Buffer中的示例
	 * @throws IOException
	 */
	public static void testChannel() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		// 创建一个容量为48的字节缓冲区
		ByteBuffer buf = ByteBuffer.allocate(48);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			System.out.println("Read " + bytesRead);
			buf.flip(); // 将buffer从写模式切换到读模式
			
			/*while(buf.hasRemaining()){
				System.out.print((char) buf.get());
			}
			System.out.println("\n"+new String(buf.array(), "utf8"));*/
			byte[] bs = new byte[bytesRead];
		
			int i = 0;
			while(buf.hasRemaining()){
				bs[i++] = buf.get();
			}
			
			bos.write(bs);
			buf.clear(); // 清空缓存区，准备写数据
			bytesRead = inChannel.read(buf);
		}
		bos.flush();
		System.out.println(new String(bos.toByteArray(), "utf8"));		
		bos.close();
		aFile.close();
	}
	
	/**
	 * 分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
	 * 聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。
	 * @throws IOException 
	 */
	public static void testScattering() throws IOException{

		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		// 创建一个容量为48的字节缓冲区
		ByteBuffer buf = ByteBuffer.allocate(10);
		ByteBuffer buf2 = ByteBuffer.allocate(50);
		ByteBuffer[] bufferArray = {buf, buf2};
		long bytesRead = inChannel.read(bufferArray);
		byte[] bus = new byte[60];
		while (bytesRead != -1) {
			System.out.println("Read " + bytesRead);			
			buf.flip(); // 将buffer从写模式切换到读模式
	
			/*while(buf.hasRemaining()){
				System.out.print((char) buf.get());
			}*/
			System.arraycopy(buf, 0, bus, 0, buf.capacity());
			buf.clear(); // 清空缓存区，准备写数据				
		}
		buf2.clear();
		buf.clear();
		System.out.println(new String(bus,"utf8"));
		aFile.close();
	}
	
	/**
	 * 通道之间的数据传输(FileChannel特有)
	 * @throws IOException 
	 */
	public static void transForm() throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile("data/fromFile.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("data/toFile.txt", "rw");		
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();
		System.out.println(count);
		// 将源通道中的数据传输目标通道:方法一
		// toChannel.transferFrom(fromChannel, position, count);
		fromChannel.transferTo(position, count, toChannel);
		
		/*ByteBuffer buf = ByteBuffer.allocate((int) count);
		toChannel.read(buf);
		System.out.println(new String(buf.array(), "utf8")+"dsa");
		toFile.write(buf.array());*/
		toFile.close();
		fromFile.close();
		
	}
}

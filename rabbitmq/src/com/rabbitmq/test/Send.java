package com.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    System.out.println("开始时间：" + System.currentTimeMillis());
    for(int i=0; i < 10000; i++) {
    	channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!" + i;
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
    }
    System.out.println("结束时间：" + System.currentTimeMillis());
    channel.close();
    connection.close();
  }
}
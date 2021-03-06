package com.xtw.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/3/11 22:00
 */
public class JMTopicProducer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.151:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Destination destination = session.createTopic("myTopic");
            //创建发送者
            MessageProducer producer = session.createProducer(destination);
            //创建需要发送的消息
            TextMessage textMessage = session.createTextMessage("TOPIC -> Hello World");
            //这个设置后，先启动发送端，发送端离线的情况下再启动接收端，接收端能直接接收到消息
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);//持久化消息
            //发送消息
            producer.send(textMessage);
            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

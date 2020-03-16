package com.xtw.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/3/11 22:11
 */
public class JMSPersisentTopicReceiver {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.151:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            String clientId = "ClientID-001";
            connection.setClientID(clientId);
            connection.start();
            Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Topic topic = session.createTopic("myTopic");
            //创建消费者
            MessageConsumer consumer = session.createDurableSubscriber(topic,clientId);
            //接收消息
            TextMessage textMessage = (TextMessage)consumer.receive();
            System.out.println(textMessage.getText());
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

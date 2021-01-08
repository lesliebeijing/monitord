package com.lesliefang.monitord;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

public class MqttClient {
    private Logger logger = LoggerFactory.getLogger(MqttClient.class);

    private MQTT mqtt;
    private BlockingConnection connection;

    public MqttClient() {
        mqtt = new MQTT();
        mqtt.setUserName("test");
        mqtt.setPassword("test");
        try {
            mqtt.setHost("life.itidtech.com", 1883);
            mqtt.setClientId("this is clientId");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        connect(); // 连接
    }

    public void publish(String topic, String message) {
        logger.debug("publish topic:{} message:{}", topic, message);
        try {
            connection.publish(topic, message.getBytes(), QoS.AT_LEAST_ONCE, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        logger.info("mqtt begin connect");
        if (connection == null) {
            connection = mqtt.blockingConnection();
            try {
                connection.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("mqtt connected");
        }
    }

    public void disconnect() {
        logger.info("mqtt disconnect");
        if (connection != null) {
            try {
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MqttClient mqttClient = new MqttClient();
        mqttClient.connect();
        System.out.println(mqttClient.connection);
    }
}

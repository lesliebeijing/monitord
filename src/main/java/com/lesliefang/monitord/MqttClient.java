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
        mqtt.setUserName("username");
        mqtt.setPassword("password");
        try {
            mqtt.setHost("life.itidtech.com", 1883);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message) {
        logger.debug("publish topic:{} message:{}", topic, message);
        connection = createConnection();
        try {
            connection.publish(topic, message.getBytes(), QoS.AT_LEAST_ONCE, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BlockingConnection createConnection() {
        if (connection == null) {
            BlockingConnection connection = mqtt.blockingConnection();
            try {
                connection.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
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
}

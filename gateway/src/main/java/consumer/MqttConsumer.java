package consumer;

import config.Config;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Administrator on 2017/10/26.
 */
public class MqttConsumer implements Consumer{

    private MqttClient client;
    private  MqttConnectOptions option;
    private String topic;

    public MqttConsumer(){
        try{
            client = new MqttClient(Config.MQTT_BROKER, "iotData", new MemoryPersistence());
            option =  new MqttConnectOptions();
            option.setCleanSession(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(MqttCallback callback,String topic){
        try{
            this.topic = topic;
            client.setCallback(callback);
            client.connect(option);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void startConsume() {
        try{
            client.subscribe(topic);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void stopConsume() {
        try{
            client.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

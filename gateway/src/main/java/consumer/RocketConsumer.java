package consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import config.Config;

/**
 * Created by Administrator on 2017/10/24.
 */
public class RocketConsumer implements  Consumer{
    DefaultMQPushConsumer consumer ;
    MessageListenerConcurrently listener;
    public RocketConsumer(String consumerGroup){
        consumer = new  DefaultMQPushConsumer(consumerGroup);
    }

    public boolean init(String urlAndPort, String instanceName, String topic, MessageListenerConcurrently listener){
        try{
            consumer.setNamesrvAddr(urlAndPort);
            consumer.setInstanceName(instanceName);
            consumer.subscribe(topic, Config.TAG);
            this.listener = listener;
            return true;
        }catch(Exception e){
            System.err.println("init RocketConsumer fail because of "+e.toString());
            return false;
        }
    }
    public void startConsume(){
        try{
            consumer.registerMessageListener(listener);
            consumer.start();
        }catch(Exception e){
            System.err.println("start RocketConsumer fail because of "+e.toString());
        }

    }
    public void stopConsume(){
        consumer.shutdown();
    }
}

package foo;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by Administrator on 2017/10/23.
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("unique_group_name_quickstart");
        producer.setNamesrvAddr("10.108.219.158:9876");
        producer.setInstanceName("QuickStartProducer");
        producer.start();


        for (int i = 0; i < 1; i++) {
            try {
                Message msg = new Message("test",// topic
                        ("Hello RocketMQ By Dy" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)// body
                );
                SendResult sendResult = producer.send(msg);

                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        producer.shutdown();
    }
}

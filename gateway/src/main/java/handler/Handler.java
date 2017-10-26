package handler;

import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface Handler  {
    public void handleMsg(Object obj);
}

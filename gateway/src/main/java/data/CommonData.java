package data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/10/24.
 */
public class CommonData {
    public LinkedBlockingQueue<String> rocketMQMsgCache;
    public ConcurrentHashMap<String,String> devicesTokens;
    public volatile static   CommonData  instance;
    private  CommonData(){
        rocketMQMsgCache  = new LinkedBlockingQueue();
        devicesTokens = new ConcurrentHashMap<String, String>();
    }

    public  static  CommonData getInstance(){
        if(instance==null){
            synchronized (CommonData.class){
                if(instance==null) instance = new CommonData();
            }
        }
        return instance;
    }

}

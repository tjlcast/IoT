package handler;

/**
 * Created by Administrator on 2017/10/24.
 */
public class DeviceMsgReceiver {
    public void receive(Object obj,String msgTag){
        if(msgTag.equals("telemetry")){
            SynDeviceTeleMagHandler.getInstance().handleMsg(obj);
        }else {
            SynDeviceAttrMsgHandler.getInstance().handleMsg(obj);
        }
    }
}

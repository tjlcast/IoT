package handler;

import config.Config;
import data.CommonData;
import data.Device;
import util.ThingsBoardApi;

/**
 * Created by Administrator on 2017/10/24.
 */
public class SynDeviceAttrMsgHandler implements  Handler{
    private static SynDeviceAttrMsgHandler instance;
    private ThingsBoardApi thingsBoardApi;
    String userTocken;

    private SynDeviceAttrMsgHandler(){
        try{
            thingsBoardApi   = ThingsBoardApi.getInstance(Config.THINGSBOARD_URL,Config.THINGSBOARD_PORT);
            userTocken = thingsBoardApi.api_token(Config.USER_NAME,Config.PASSWORD) ;
        }catch(Exception e){
            System.err.println("init SynDeviceAttrMsgHandler failed");
        }
    }

    public static SynDeviceAttrMsgHandler getInstance(){
        if (instance==null){
            synchronized (SynDeviceTeleMagHandler.class){
                if (instance==null) instance = new SynDeviceAttrMsgHandler();
            }
        }
        return instance;
    }
    public void handleMsg(Object obj){

        Device device = (Device)obj;
        System.out.println("handle a attr msg ="+device.toString());
        try {
            if (CommonData.getInstance().devicesTokens.containsKey(device.getuId())) {
                String deviceToken = CommonData.getInstance().devicesTokens.get(device.getuId());
                thingsBoardApi.api_attributes(userTocken, deviceToken, device.getInfo());
            } else {
                String deviceId = thingsBoardApi.api_device(userTocken, device.getDeviceName(), "default");
                String deviceToken = thingsBoardApi.api_accessToken(userTocken, deviceId);
                thingsBoardApi.api_attributes(userTocken, deviceToken, device.getInfo());
                CommonData.getInstance().devicesTokens.put(device.getuId(), deviceToken);
            }
        }catch(Exception e){
            System.err.println("fail to handle msg ");
            try{
                userTocken = thingsBoardApi.api_token(Config.USER_NAME,Config.PASSWORD) ;
            }catch(Exception e1){
                e1.printStackTrace();
            }

        }
    }
}

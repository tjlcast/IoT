package cn.edu.bupt.asyn;

/**
 * Created by Administrator on 2017/10/23.
 */
public interface HListener<T>{
    public void onSuccess(T responce);
    public void onFail(Exception e);
}

package cn.edu.bupt.asyn;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/10/23.
 */
public class HttpTask<T> extends FutureTask<T> {

    HListener listener;

    public HttpTask(Callable<T> call){
        super(call);
    }

    @Override
    protected void done(){
        if(listener==null) return ;
        try {
            T res = this.get();
            listener.onSuccess(res);
        }catch(Exception e){
            listener.onFail(e);
        }
    }

    public void addListener(HListener<T> listener){
        this.listener = listener;
    }
}

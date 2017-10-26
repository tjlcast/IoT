package cn.edu.bupt.asyn;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/10/23.
 */
public class Main {
    public static void main(String[] args){
        ExecutorService service =  Executors.newCachedThreadPool();
        service.submit(()->{
            System.out.print("haha");
            return null;
        });
//        HttpTask task =  new HttpTask<String>(new Callable<String>() {
//            public String call() throws Exception {
//                return "hahah";
//            }
//        });
//
//        task.addListener(new HListener() {
//            public void onSuccess(Object responce) {
//                System.out.print(responce.toString());
//            }
//
//            public void onFail(Exception e) {
//               e.printStackTrace();
//            }
//        });
//        service.submit(task);
    }
}

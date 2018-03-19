package org.xcraftman;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/28.
 */
public class FileAppRunner {

    public static void main(String[] args){


        /*
        *
        * */
        int thread_num = Integer.parseInt(args[0]);
        int payload_length = Integer.parseInt(args[1]);
        int payload_num = Integer.parseInt(args[2]);



        MongoClient mongoClient= new MongoClient(new MongoClientURI("mongodb://172.27.203.8:27017/test"));

        // get handle to "mydb" database
        MongoDatabase database = mongoClient.getDatabase("mydb");
        database.drop();
        mongoClient.close();




        ExecutorService executorService = Executors.newFixedThreadPool(thread_num);
        MongoRunner mongoRunner = new MongoRunner(new Payload(payload_length), payload_num);
        for (int i =0; i < thread_num; i++){
            executorService.submit(mongoRunner);
        }

        try {
            long currentMillis = System.currentTimeMillis();
            executorService.shutdown();
            executorService.awaitTermination(300, TimeUnit.MINUTES);
            long timeElapseInSecs = (System.currentTimeMillis() - currentMillis) /1000;

            for (Long timeElapseInLoop : mongoRunner.getTimeElapseInLoopList()){
                System.out.println(" upload done : " + timeElapseInLoop + " mill sec");
            }


            System.out.println("Total: " + (payload_num * thread_num)  + " Payload length : " + payload_length + " Elapse : " + timeElapseInSecs + " sec");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

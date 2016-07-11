package impatient.java.lang.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xianguanghuang on 2016/6/3. For Note and Demo purpose
 */
public class TestStackSizeLimit {

    public static void main(String[] args){
        List<Thread> list = new ArrayList<>();
        for (int i = 0 ; i < 1000000; i++){

            Thread e = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            e.start();
            list.add(e);
        }
    }
}

package impatient.springboot.rammbitmq;

/**
 * Created by Administrator on 2016/4/12.
 */
import java.util.concurrent.CountDownLatch;

public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
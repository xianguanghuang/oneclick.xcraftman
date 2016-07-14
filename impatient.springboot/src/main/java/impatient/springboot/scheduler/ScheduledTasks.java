package impatient.springboot.scheduler;

/**
 * Created by xianguanghuang on 2016/6/10. For Note and Demo purpose
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {



    @Scheduled(cron  = "*/10 * * * * *" )
    public void reportCurrentTime() throws InterruptedException {
        method1();
    }

    @Scheduled(cron  = "*/10 * * * * *" )
    public void reportCurrentTime2() throws InterruptedException {
        method2();
    }

    @Scheduled(cron  = "*/10 * * * * *" )
    public void reportCurrentTime3() throws InterruptedException {
        method3();
    }


    public void method1() throws InterruptedException {
        System.out.println("reportCurrent Time task" );
        Thread.sleep(1000*30);
    }


    public void method2() throws InterruptedException {
        System.out.println("reportCurrent2 Time task" );
        Thread.sleep(1000*30);
    }


    public void method3() throws InterruptedException {
        System.out.println("reportCurrent3 Time task" );
        Thread.sleep(1000*30);
    }
}

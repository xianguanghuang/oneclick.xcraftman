package impatient.springboot.rammbitmq;

/**
 * Created by Administrator on 2016/4/12.
 */

import org.apache.commons.io.IOUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileInputStream;

@SpringBootApplication
public class Application implements CommandLineRunner {

    final static String queueName = "queue.crash.report";

    @Autowired
    AnnotationConfigApplicationContext context;

    @Autowired
    RabbitTemplate rabbitTemplate;


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String path = "C:\\Users\\Administrator\\Downloads\\lecture-20151219am-honghai.zip";
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(path));

        System.out.println("Sending message...");

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("crash", "{\"appId\" = \"yymand\"}");
        Message message = new Message(bytes, messageProperties);

        long startTimeInMilli = System.currentTimeMillis();
        for (int i = 0; i < 5 ; i ++)
            rabbitTemplate.send(queueName,message);

        System.out.println((System.currentTimeMillis() - startTimeInMilli) / 1000);
        context.close();

    }
}
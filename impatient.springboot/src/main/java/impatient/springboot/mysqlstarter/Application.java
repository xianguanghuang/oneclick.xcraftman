package impatient.springboot.mysqlstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/20.
 */
@EnableScheduling
@Configuration
@EnableAutoConfiguration
@Import({ JdbcClient.class })
public class Application implements CommandLineRunner {

    @Autowired
    private JdbcClient jdbcClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //已经成功运行sql 查询
        List<Map<String, Object>> maps = jdbcClient.fireQuery();

        //todo 增加读文件逻辑

        //todo 使用jdbc client 将数据导入本地数据库
        System.out.println();
    }
}

package impatient.springboot.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xianguanghuang on 15-4-12.
 */
@Controller
@EnableAutoConfiguration
public class CustomizedMetricController {

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

   // @RequestMapping("/")
    //@ResponseBody
    String anyRequest() {

        // counterService 是基本的技术功能
        counterService.increment("services.system.customized.metric.controller.anyRequest.counter.invoked");
        // gaugeService 是需要提交一个可度量的数值
        gaugeService.submit("services.system.customized.metric.controller.anyRequest.gauge.invoked", 100);


        return "Any Request";
    }

    public static void main2(String[] args) throws Exception {
        SpringApplication.run(CustomizedMetricController.class, args);
    }
}

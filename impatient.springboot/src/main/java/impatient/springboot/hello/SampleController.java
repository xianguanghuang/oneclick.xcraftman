package impatient.springboot.hello;

import impatient.springboot.configuration.ApplyPropertiesConfiguration;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
@Import(ApplyPropertiesConfiguration.class)
public class SampleController<E> {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {


        SpringApplication.run(SampleController.class, args);
    }


}

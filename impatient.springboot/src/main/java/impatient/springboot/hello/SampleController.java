package impatient.springboot.hello;

import impatient.springboot.configuration.ApplyPropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@Import(ApplyPropertiesConfiguration.class)
public class SampleController<E> {

    @RequestMapping("/")
    @ResponseBody
    String home() {

        return "Hello World!";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void processFooBar(FooBar fooBar) {
        //Do stuff
        System.out.println(fooBar.getBar());
    }

    public static void main(String[] args) throws Exception {


        SpringApplication.run(SampleController.class, args);
    }


}

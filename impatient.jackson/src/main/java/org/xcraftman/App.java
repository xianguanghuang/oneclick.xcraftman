package org.xcraftman;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.xcraftman.jackson.Foo;
import org.xcraftman.jackson.User;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        User user = mapper.readValue(new File("C:\\repository\\oneclick.xcraftman\\impatient.jackson\\src\\main\\java\\org\\xcraftman\\jackson\\user.json"), User.class);

        Map<String,Object> userData = mapper.readValue(new File("C:\\repository\\oneclick.xcraftman\\impatient.jackson\\src\\main\\java\\org\\xcraftman\\jackson\\user.json"), Map.class);

        Map<String,User> result = mapper.readValue(new File("C:\\repository\\oneclick.xcraftman\\impatient.jackson\\src\\main\\java\\org\\xcraftman\\jackson\\user_v2.json"),
                new TypeReference<Map<String,User>>() { });

        String json = "[{\"foo\": \"bar\"},{\"foo\": \"biz\"}]";
        JsonFactory f = new JsonFactory();
        JsonParser jp = f.createJsonParser(json);
        // advance stream to START_ARRAY first:
        jp.nextToken();
        // and then each time, advance to opening START_OBJECT
        while (jp.nextToken() == JsonToken.START_OBJECT) {
            Foo foobar = mapper.readValue(jp, Foo.class);
            // process
            // after binding, stream points to closing END_OBJECT

        }


        System.out.println("Hello World!");
    }
}

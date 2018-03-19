package impatient.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ProtostuffService {

    private RuntimeSchema<Person> schema = RuntimeSchema.createFrom(Person.class);

    public void demoProtostuff(Person person){


        byte[] bytes = ProtobufIOUtil.toByteArray(person, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        Person personForDeserialized = schema.newMessage();
        ProtobufIOUtil.mergeFrom(bytes, personForDeserialized, schema);

        System.out.println(personForDeserialized.getAge());
        System.out.println(personForDeserialized.getName());


    }

    public static void main(String[] args){

        Person person = new Person(10, "xianguang");
        ProtostuffService protostuffService = new ProtostuffService();
        protostuffService.demoProtostuff(person);
    }


}

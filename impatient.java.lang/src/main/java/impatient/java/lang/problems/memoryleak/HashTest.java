package impatient.java.lang.problems.memoryleak;


import java.util.HashMap;

/**
 * Created by xianguanghuang on 2016/4/30. For Note and Demo purpose
 */
public class HashTest {
    class Person{
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int hashCode(){
            return age ;
        }

        @Override
        public boolean equals(Object thatObject){

            Person thatPerson = (Person) thatObject;
            if (thatPerson.name.equals(this.name) && thatPerson.age == this.age)
                return true;
            return false;
        }
    }

    public void runTest(){
        Person person1 = new Person(33, "huangxianguang");
        Person person2 = new Person(34, "huangxinmiao");

        HashMap<Person,String> hashMap = new HashMap<>();
        hashMap.put(person1, "one");
        hashMap.put(person2, "two");





        System.out.println(hashMap.get(person1));
        System.out.println(hashMap.get(person2));
    }
    public static void main(String[] args){
        System.out.println("ab".hashCode());
        System.out.println("bc".hashCode());
        new HashTest().runTest();

    }
}

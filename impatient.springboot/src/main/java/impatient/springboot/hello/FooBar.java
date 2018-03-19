package impatient.springboot.hello;

/**
 * Created by Administrator on 2016/4/19.
 */
public class FooBar {

    private String bar;
    private String foo;

    public void setBar(String bar) {
        this.bar = bar;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    FooBar(){


    }

    FooBar(String bar, String foo) {
        this.bar = bar;
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public String getFoo() {
        return foo;
    }
}
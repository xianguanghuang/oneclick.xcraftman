package impatient.java.lang.nio;

/**
 * Created by xianguanghuang on 2016/4/10. For Note and Demo purpose
 */
public class Nutrition {

    private int member1;
    private int member2;

    public Nutrition(int member1) {
        this.member1 = member1;

    }

    public Nutrition(int member1, int member2) {
        this.member1 = member1;

    }


}

class NutritionUtil {

    public void useNutrition(){
        Nutrition nutrition = new Nutrition(1);
    }
}
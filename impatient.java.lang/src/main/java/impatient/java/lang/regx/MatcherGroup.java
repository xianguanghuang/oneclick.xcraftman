package impatient.java.lang.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xianguanghuang on 2016/5/27. For Note and Demo purpose
 */
public class MatcherGroup {

    public static void main(String[] args){
        Pattern pattern = Pattern.compile("(.*?)(\\d+)(.*)");
        Matcher matcher = pattern.matcher("this is line with 4444440! xxxxx");
        if (matcher.find()){
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));

        }

    }
}

package impatient.java.lang.multithreads.testing;

/**
 * Created by Administrator on 2016/5/27.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches
{
    public static void main( String args[] ){

        // String to be scanned to find the pattern.
        //String line = "{\"description\":\"uid:987654321,testKey1:testValue1\"}";
        String line = "{\"description\":\"uid:275159671\"}";
        String pattern = "(.*)uid:(\\d+)(.*)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(null);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(10) );
        } else {
            System.out.println("NO MATCH");
        }
    }
}
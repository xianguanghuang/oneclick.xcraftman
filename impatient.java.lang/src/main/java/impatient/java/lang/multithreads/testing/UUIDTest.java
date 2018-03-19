package impatient.java.lang.multithreads.testing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2016/4/29.
 */
public class UUIDTest {
    public static void main(String[] args) {
        // creating byte array
        String crashDevice = "2D48DF2D-01B8-4618-8459-0C74106FFF6A";
        String launch_time = "2015-08-27 10:24:12";
        String appId = "yymand";

        String reportId = crashDevice + launch_time + appId;

        Date date = Calendar.getInstance().getTime();
        System.out.println(date.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");



        // creating UUID from byte
        UUID uid = UUID.nameUUIDFromBytes(reportId.getBytes());

        // checking UUID value
        System.out.println("UUID value from byte: "+uid.toString());
    }
}

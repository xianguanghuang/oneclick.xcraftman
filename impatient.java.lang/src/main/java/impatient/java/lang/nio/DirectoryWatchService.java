package impatient.java.lang.nio;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;
/**
 * Created by xianguanghuang on 2016/3/9. For Note and Demo purpose
 */
public class DirectoryWatchService {

    public static void main(String[] args){


        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path dir = FileSystems.getDefault().getPath("E:/Monitor/");
            dir.register(watchService, ENTRY_MODIFY);
            dir.register(watchService, ENTRY_CREATE);
            dir.register(watchService, ENTRY_DELETE);

            WatchKey watchKey = watchService.take();
            for (WatchEvent<?> watchEvent : watchKey.pollEvents()){
                if (watchEvent.kind() == ENTRY_MODIFY){

                    System.out.println("Fire ENTRY MODIFY");
                }

                if (watchEvent.kind() == ENTRY_CREATE){
                    System.out.println("Fire create");
                }

                if (watchEvent.kind() == ENTRY_DELETE){
                    System.out.println("Fire delete");
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


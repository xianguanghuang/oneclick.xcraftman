/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.testing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemonstrateMultithread {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            es.submit(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    while (true) {
                        Thread.currentThread();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }

}

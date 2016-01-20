/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.cancellation_and_shutdown.stopping_a_thread_base_service;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/*
 * 使用poison 来中止服务
 * */
public class IndexingService {
    private static final File POISION = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    class CrawlerThread extends Thread {

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                /* fall through */
            } finally {
                while (true) {
                    try {
                        queue.put(POISION);
                        break;
                    } catch (InterruptedException el) {
                        /* retry */
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            // TODO Auto-generated method stub

        }
    }

    class IndexerThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISION) {
                        break;
                    } else {
                        indexFile(file);
                    }
                }
            } catch (InterruptedException consumed) {

            }
        }

        private void indexFile(File file) {
            // TODO Auto-generated method stub

        }
    }

    public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

}

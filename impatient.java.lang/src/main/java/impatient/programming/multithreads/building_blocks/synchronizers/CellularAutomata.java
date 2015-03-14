/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.building_blocks.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * 这是一个模拟程序:
 * 假设有一个mainBoard(问题N) ， 将其拆分为多个submainboard(q/N), 有N 个worker 去处理.
 * 每个worker 处理完自己的subMainBoard 后， 就会触发barrier减一, 如果其他worker 还没处理完则barrier的
 * await方法会阻塞.
 * 当一整个mainBoard 处理完后（所有worker 都已经调用barrier 的await 方法），
 * barrier 的值为0,await 方法会放开. barrier 的runnable 方法会执行
 * 
 * TODO : 验证barrier 的runnable 方法会不会在所有worker运行完后就执行。
 * */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {

            @Override
            public void run() {
                mainBoard.commitNewValues();
            }
        });

        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    return;
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    return;
                }
            }
        }

        private int computeValue(int x, int y) {
            // TODO Auto-generated method stub
            return 0;
        }
    }

}

class Board {
    public boolean hasConverged() {
        return false;
    }

    public Board getSubBoard(int count, int i) {
        // TODO Auto-generated method stub
        return null;
    }

    public void commitNewValues() {
        // TODO Auto-generated method stub

    }

    public int getMaxX() {
        //no impl
        return -1;
    }

    public int getMaxY() {
        //no impl
        return -1;
    }

    public void setNewValue(int x, int y, int newValue) {

    }

    public void waitForConvergence() {

    }
}

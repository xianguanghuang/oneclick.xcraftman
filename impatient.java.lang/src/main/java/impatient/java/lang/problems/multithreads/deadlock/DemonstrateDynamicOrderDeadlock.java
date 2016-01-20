/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.problems.multithreads.deadlock;

import java.util.Random;

/**
 * DemonstrateDeadlock
 * <p/>
 * Driver loop that induces deadlock under typical conditions
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class DemonstrateDynamicOrderDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final DynamicOrderDeadlock.Account[] accounts = new DynamicOrderDeadlock.Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new DynamicOrderDeadlock.Account();

        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    DynamicOrderDeadlock.DollarAmount amount = new DynamicOrderDeadlock.DollarAmount(rnd.nextInt(1000));
                    try {
                        DynamicOrderDeadlock.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (DynamicOrderDeadlock.InsufficientFundsException ignored) {
                        System.out.println();
                    }
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}

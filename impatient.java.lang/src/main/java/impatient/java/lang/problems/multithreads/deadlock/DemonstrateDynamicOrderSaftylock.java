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
public class DemonstrateDynamicOrderSaftylock {
    private static final int NUM_THREADS = 200;
    private static final int NUM_ACCOUNTS = 50;
    private static final int NUM_ITERATIONS = 10000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final DynamicOrderSaftylock.Account[] accounts = new DynamicOrderSaftylock.Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new DynamicOrderSaftylock.Account();

        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    DynamicOrderSaftylock.DollarAmount amount = new DynamicOrderSaftylock.DollarAmount(
                            rnd.nextInt(1000));
                    try {
                        DynamicOrderSaftylock.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (DynamicOrderSaftylock.InsufficientFundsException ignored) {

                    }
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}

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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * DynamicOrderDeadlock
 * <p/>
 * Dynamic lock-ordering deadlock
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class DynamicOrderSaftylock {

    final static Object tieLock = new Object();

    public static void transferMoney(final Account fromAccount, final Account toAccount, final DollarAmount amount)
            throws InsufficientFundsException {

        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }

            }
        }

        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);

        if (fromHash < toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    new Helper().transfer();
                }

            }
        } else if (fromHash > toHash) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }

    }

    static class DollarAmount implements Comparable<DollarAmount> {
        // Needs implementation
        private int amount;

        public DollarAmount(int amount) {
            this.amount = amount;
        }

        public DollarAmount add(DollarAmount d) {
            return new DollarAmount(this.amount + d.amount);
        }

        public DollarAmount subtract(DollarAmount d) {
            return new DollarAmount(this.amount - d.amount);
        }

        public int compareTo(DollarAmount dollarAmount) {
            return (this.amount - dollarAmount.amount) > 0 ? 1 : -1;
        }

    }

    static class Account {
        private DollarAmount balance;
        private final int acctNo;
        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            acctNo = sequence.incrementAndGet();
            balance = new DollarAmount(0);
        }

        void debit(DollarAmount d) {
            balance = balance.subtract(d);
        }

        void credit(DollarAmount d) {
            balance = balance.add(d);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }
    }

    static class InsufficientFundsException extends Exception {
    }
}

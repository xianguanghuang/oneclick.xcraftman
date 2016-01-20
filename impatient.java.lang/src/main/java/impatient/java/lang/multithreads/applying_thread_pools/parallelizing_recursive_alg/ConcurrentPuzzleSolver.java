/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.applying_thread_pools.parallelizing_recursive_alg;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentPuzzleSolver<P, M> {

    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec = Executors.newFixedThreadPool(100);
    private final ConcurrentMap<P, Boolean> seen;
    final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ConcurrentMap<P, Boolean> seen) {
        super();
        this.puzzle = puzzle;
        this.seen = seen;
    }

    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            // block until solution found
            Node<P, M> solnNode = solution.getValue();
            return (solnNode == null) ? null : solnNode.asMoveList();
        } finally {
            exec.shutdown();
        }
    }

    protected Runnable newTask(P p, M m, Node<P, M> n) {
        return new SolverTask(p, m, n);
    }

    class SolverTask extends Node<P, M> implements Runnable {

        public SolverTask(P pos, M move, Node<P, M> prev) {
            // 
            super(pos, move, prev);
        }

        @Override
        public void run() {
            if (solution.isSet() || (seen.putIfAbsent(pos, true) != null)) {
                return;
            }
            if (puzzle.isGoal(pos)) {
                solution.setValue(this);
            } else {
                for (M m : puzzle.legalMoves(pos)) {
                    exec.execute(newTask(puzzle.move(pos, m), m, this));
                }
            }

        }

    }
}

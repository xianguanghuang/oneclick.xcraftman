/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.problems.memoryleak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemostrateMemoryLeak {

    private static final String LEAK_LIST_KEY = "leakList";
    private Map<String, List<byte[]>> listHolder = new HashMap<String, List<byte[]>>();

    public List<byte[]> getList() {
        List<byte[]> leakList = listHolder.get(LEAK_LIST_KEY);
        if (leakList == null) {
            leakList = new ArrayList<byte[]>();
            listHolder.put(LEAK_LIST_KEY, leakList);
            return leakList;
        }

        return leakList;
    }

    public void clearLeakObject() {
        if (listHolder.get(LEAK_LIST_KEY) != null) {
            listHolder.remove(LEAK_LIST_KEY);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        DemostrateMemoryLeak demostrateMemoryLeak = new DemostrateMemoryLeak();
        for (int i = 0; i < 100000; i++) {
            byte[] bt = new byte[10000];
            demostrateMemoryLeak.getList().add(bt);

        }

        Thread.currentThread().join();
    }

}

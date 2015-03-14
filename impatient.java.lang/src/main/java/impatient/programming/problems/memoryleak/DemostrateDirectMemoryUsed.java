/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.problems.memoryleak;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DemostrateDirectMemoryUsed {

    public static void main(String[] args) {
        List leakList = new ArrayList();
        while (true) {
            ByteBuffer bb = ByteBuffer.allocateDirect(100000);
            //leakList.add(bb);
        }
    }
}

/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.problems.highcpu;

public class Infinifyloops {

    public static void main(String[] args) {

        System.out.println("Please, Monitor your CPU usage by system util.");

        System.out.println("After Checking, Just Kill this Process");
        int i = 0;
        while (true) {
            i++;
        }
    }
}

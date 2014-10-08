/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.sharingobjects.threadconfinement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 使用标准库里面的ThreadLocal ,保证同一个线程里面所对应的值是唯一的
 * */
public class ThreadLocalConfiement {

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
        @Override
        public Connection initialValue() {
            try {
                return DriverManager.getConnection("DB_URL");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }

}

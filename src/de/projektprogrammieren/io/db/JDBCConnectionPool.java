package de.projektprogrammieren.io.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.projektprogrammieren.util.ObjectPool;

/**
 * Quelle: https://sourcemaking.com/design_patterns/object_pool/java
 * @author mbj
 *
 */

public class JDBCConnectionPool extends ObjectPool<Connection> {

	  private String dsn, usr, pwd;

	  public JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
	    super();
	    try {
	      Class.forName(driver).newInstance();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    this.dsn = dsn;
	    this.usr = usr;
	    this.pwd = pwd;
	  }

	  @Override
	  protected Connection create() {
	    try {
	      return (DriverManager.getConnection(dsn, usr, pwd));
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return (null);
	    }
	  }

	  @Override
	  public void expire(Connection o) {
	    try {
	      ((Connection) o).close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }

	  @Override
	  public boolean validate(Connection o) {
	    try {
	      return (!((Connection) o).isClosed());
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return (false);
	    }
	  }
	}



//public class Main {
//	  public static void main(String args[]) {
//	    // Do something...
//	    ...
//
//	    // Create the ConnectionPool:
//	    JDBCConnectionPool pool = new JDBCConnectionPool(
//	      "org.hsqldb.jdbcDriver", "jdbc:hsqldb://localhost/mydb",
//	      "sa", "secret");
//
//	    // Get a connection:
//	    Connection con = pool.checkOut();
//
//	    // Use the connection
//	    ...
//
//	    // Return the connection:
//	    pool.checkIn(con);
//	 
//	  }
//	}
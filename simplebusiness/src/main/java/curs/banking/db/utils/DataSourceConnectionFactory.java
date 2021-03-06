package curs.banking.db.utils;

import java.sql.Connection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
//import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ApplicationScoped
public class DataSourceConnectionFactory implements IConnectionFactory {
  //@Resource(lookup="java:/comp/env/jdbc/BankDB")
  @Inject
  private DataSource mDataSource;

  /**
  private DataSourceConnectionFactory() {
    InitialContext cxt;
    try {
      cxt = new InitialContext();
      mDataSource = (DataSource) cxt.lookup("java:/comp/env/jdbc/BankDB");
      if (mDataSource == null) {
        throw new RuntimeException("Data source not found!");
      }
    } catch (NamingException e) {
      throw new RuntimeException(e);
    }
  }
  */

  @Override
  public Connection getConnection() throws Exception {
    return mDataSource.getConnection();
  }

  /**
  private static DataSourceConnectionFactory __INSTANCE = null;

  public static DataSourceConnectionFactory factory() {
    if (__INSTANCE == null) {
      synchronized (DataSourceConnectionFactory.class) {
        if (__INSTANCE == null) {
          __INSTANCE = new DataSourceConnectionFactory();
        }
      }
    }
    return __INSTANCE;
  }
  */
}

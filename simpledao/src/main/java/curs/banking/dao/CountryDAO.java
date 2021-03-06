package curs.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import curs.banking.model.Country;
import static curs.banking.dao.SQLUtils.closeQuietly;

public class CountryDAO extends AbstractBaseDAO<Country> {

  public CountryDAO(Connection pConnection) {
    super(pConnection);
  }

  @Override
  protected Country loadFromResultSet(ResultSet pRS) throws SQLException {
    long id = pRS.getLong(1);
    String name = pRS.getString(2);
    return new Country(id, name);
  }

  @Override
  protected String getSQLForFindAll() {
    return "SELECT ID,NAME FROM BANK.COUNTRY";
  }

  public Country findByName(String pName) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("SELECT ID,NAME FROM BANK.COUNTRY WHERE UPPER(NAME)=?");
      stmt.setString(1, pName.trim().toUpperCase());
      rs = stmt.executeQuery();
      if (rs.next()) {

        return loadFromResultSet(rs);
      } else {
        // throw new DAOException("Id not found:" + pId);
        return null;
      }
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      closeQuietly(rs, stmt);
    }
  }

  @Override
  public Country insert(Country pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("INSERT INTO BANK.COUNTRY(NAME) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, pEntity.getName());
      stmt.executeUpdate();
      rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        long id = rs.getLong(1);
        return findById(id);
      } else {
        throw new DAOException("Id not found in insert");
      }
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      closeQuietly(rs, stmt);
    }
  }

  @Override
  public Country update(Country pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("UPDATE BANK.COUNTRY SET NAME=? WHERE ID=?");
      stmt.setString(1, pEntity.getName());
      stmt.setLong(2, pEntity.getId());
      stmt.executeUpdate();
      return findById(pEntity.getId());
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      closeQuietly(rs, stmt);
    }
  }

  @Override
  public void delete(Country pEntity) {
    throw new DAOException("Not implemented!!!");
  }

}

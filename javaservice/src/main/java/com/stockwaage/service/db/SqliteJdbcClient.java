package com.stockwaage.service.db;

import com.google.common.collect.Lists;

import com.stockwaage.service.weights.Weight;
import com.stockwaage.service.weights.WeightUnit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class SqliteJdbcClient {

  private static final String CONNECTION_URL = "jdbc:sqlite:loadCells.db";
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd " +
      "HH:mm:SS");

  public List<Weight> findWeights() throws ClassNotFoundException, SQLException {
    List<Weight> weights = Lists.newArrayList();
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try {
      connection = DriverManager.getConnection(CONNECTION_URL);
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("select * from weights");
      while (rs.next()) {
        Weight weight = new Weight(rs.getString("loadCellId"), WeightUnit.valueOf(rs.getString
            ("weightUnit")), rs.getDouble("weightValue"), rs.getTimestamp("measurementTime").toLocalDateTime());
        weights.add(weight);
      }

    } catch (SQLException e) {
      throw e;
    } finally {
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        throw e;
      }
    }
    return weights;
  }

  public void insert(Weight weight) throws ClassNotFoundException, SQLException {
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try {
      connection = DriverManager.getConnection(CONNECTION_URL);
      Statement statement = connection.createStatement();

      String timestamp = weight.timestamp().format(FORMATTER);

      statement.executeUpdate("create table if not exists weights (loadCellId string, weightUnit string, weightValue double, measurementTime timestamp)");
      statement.executeUpdate("insert into weights (loadCellId, weightUnit, weightValue, measurementTime) values ('" + weight.loadCellId() + "', '" + weight.weightUnit() + "', " + weight.value() + ", " + System.currentTimeMillis() + ")");
    } catch (SQLException e) {
      throw e;
    } finally {
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        throw e;
      }
    }
  }

}

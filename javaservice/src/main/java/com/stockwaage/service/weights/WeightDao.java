package com.stockwaage.service.weights;

import com.google.common.collect.Lists;

import com.stockwaage.service.db.SqliteJdbcClient;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeightDao {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd " +
      "HH:mm:ss");

  private final SqliteJdbcClient sqliteJdbcClient;

  public WeightDao(SqliteJdbcClient sqliteJdbcClient){
    this.sqliteJdbcClient = sqliteJdbcClient;
  }

  public List<Weight> allCurrentWeights() {
    return Lists.newArrayList(new Weight("loadCell1", WeightUnit.KILOGRAMM, 20, LocalDateTime.now()),
        new Weight
            ("loadCell2", WeightUnit.KILOGRAMM, 33, LocalDateTime.now()), new Weight("loadCell3",
            WeightUnit.GRAMM,
            55, LocalDateTime.now()));
  }

  public List<Weight> currentWeightsBy(String loadCellId) {
    return Lists.newArrayList(new Weight("loadCell1", WeightUnit.KILOGRAMM, 20, LocalDateTime.now
        ()));
  }

  public List<Weight> historicWeightsBy(String loadCell) {
    LocalDateTime dateTime = LocalDateTime.parse("2015-09-01 10:15:05", DATE_TIME_FORMATTER);
    LocalDateTime dateTime2 = LocalDateTime.parse("2015-09-03 15:40:05", DATE_TIME_FORMATTER);
    LocalDateTime dateTime3 = LocalDateTime.parse("2015-09-04 11:02:05", DATE_TIME_FORMATTER);
    return Lists.newArrayList(new Weight("loadCell1", WeightUnit.KILOGRAMM, 20, dateTime), new Weight
        ("loadCell1", WeightUnit.KILOGRAMM, 33, dateTime2), new Weight("loadCell1", WeightUnit
        .GRAMM, 55, dateTime3));
  }

  public List<Weight> allHistoricWeights() throws ClassNotFoundException, SQLException{
   return sqliteJdbcClient.findWeights();
  }
}

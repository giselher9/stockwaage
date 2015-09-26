package com.stockwaage.service.loadcell;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stockwaage.service.db.SqliteJdbcClient;
import com.stockwaage.service.weights.Weight;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoadCellSyncer {

  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);
  private final HttpLoadCellConnector loadCellConnector;
  private final SqliteJdbcClient sqliteJdbcClient;

  public LoadCellSyncer(HttpLoadCellConnector loadCellConnector, SqliteJdbcClient sqliteJdbcClient) {
    this.loadCellConnector = loadCellConnector;
    this.sqliteJdbcClient = sqliteJdbcClient;
  }

  public void start() {
    scheduler.scheduleAtFixedRate(new Runnable() {
      public void run() {
        syncWeights();
      }
    }, 0, 2, TimeUnit.MINUTES);
  }

  private void syncWeights() {
    try {
      Weight weight = loadCellConnector.currentWeight();
      sqliteJdbcClient.insert(weight);
    } catch (JsonMappingException e) {
      System.out.println(e.getStackTrace());
    } catch (JsonParseException e) {
      System.out.println(e.getStackTrace());
    } catch (IOException e) {
      System.out.println(e.getStackTrace());
    } catch (ClassNotFoundException e) {
      System.out.println(e.getStackTrace());
    } catch (SQLException e) {
      System.out.println(e.getStackTrace());
    }
  }

}

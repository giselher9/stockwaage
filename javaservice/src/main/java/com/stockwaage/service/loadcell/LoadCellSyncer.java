package com.stockwaage.service.loadcell;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stockwaage.service.weights.Weight;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoadCellSyncer {

  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);
  private final HttpLoadCellConnector loadCellConnector;

  public LoadCellSyncer(HttpLoadCellConnector loadCellConnector) {
    this.loadCellConnector = loadCellConnector;
    scheduler.scheduleAtFixedRate(new Runnable() {
      public void run() {
        syncWeights();
      }
    }, 0, 5, TimeUnit.MINUTES);
  }

  private void syncWeights() {
    try {
      Weight weight = loadCellConnector.currentWeight();
    } catch (JsonMappingException e) {
      //todo
    } catch (JsonParseException e) {
      //todo
    } catch (IOException e) {
      //todo
    }
  }

}

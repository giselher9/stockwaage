package com.stockwaage.service.weights;

import java.time.LocalDateTime;

public class Weight {

  private String loadCellId;
  private WeightUnit weightUnit;
  private double value;
  LocalDateTime timestamp;

  public Weight(String loadCellId, WeightUnit weightUnit, double value, LocalDateTime timestamp) {
    this.loadCellId = loadCellId;
    this.weightUnit = weightUnit;
    this.value = value;
    this.timestamp = timestamp;
  }

  public String loadCellId() {
    return loadCellId;
  }

  public WeightUnit weightUnit() {
    return weightUnit;
  }

  public double value() {
    return value;
  }

  public LocalDateTime timestamp() {
    return timestamp;
  }
}

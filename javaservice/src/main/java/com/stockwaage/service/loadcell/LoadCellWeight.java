package com.stockwaage.service.loadcell;

public class LoadCellWeight {

  private String sensor;
  private String type;
  private String unit;
  private double value;

  String sensor() {
    return sensor;
  }

  void setSensor(String sensor) {
    this.sensor = sensor;
  }

  String type() {
    return type;
  }

  void setType(String type) {
    this.type = type;
  }

  String unit() {
    return unit;
  }

  void setUnit(String unit) {
    this.unit = unit;
  }

  double value() {
    return value;
  }

  void setValue(double value) {
    this.value = value;
  }
}

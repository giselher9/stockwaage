package com.stockwaage.service.resources.weights;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stockwaage.service.resources.LocalDateTimeSerializer;
import com.stockwaage.service.weights.WeightUnit;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement(name = "weight")
@XmlAccessorType(FIELD)
public class WeightRepresentation {

  @JsonProperty
  private String loadCellId;
  @JsonProperty
  private WeightUnit weightUnit;
  @JsonProperty
  private double value;
  @JsonProperty
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime timestamp;

  public WeightRepresentation(String loadCellId, WeightUnit weightUnit, double value,
                              LocalDateTime timestamp) {
    this.loadCellId = loadCellId;
    this.weightUnit = weightUnit;
    this.value = value;
    this.timestamp = timestamp;
  }

  String getLoadCellId() {
    return loadCellId;
  }

  WeightUnit getWeightUnit() {
    return weightUnit;
  }

  double getValue() {
    return value;
  }

  LocalDateTime getTimestamp() {
    return timestamp;
  }
}

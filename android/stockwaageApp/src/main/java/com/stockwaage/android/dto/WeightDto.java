package com.stockwaage.android.dto;

public class WeightDto {

    private String loadCellId;
    private String weightUnit;
    private double value;
    String timestamp;

    public WeightDto(String loadCellId, String weightUnit, double value, String timestamp) {
        this.loadCellId = loadCellId;
        this.weightUnit = weightUnit;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getLoadCellId() {
        return loadCellId;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public double getValue() {
        return value;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

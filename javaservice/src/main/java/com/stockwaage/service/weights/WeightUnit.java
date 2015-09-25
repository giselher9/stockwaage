package com.stockwaage.service.weights;

public enum WeightUnit {
  GRAMM, KILOGRAMM, UNDEFINED;

  public static WeightUnit fromString(String input){
    switch (input){
      case "kg": return KILOGRAMM;
      case "g": return GRAMM;
      default: return UNDEFINED;
    }
  }

}
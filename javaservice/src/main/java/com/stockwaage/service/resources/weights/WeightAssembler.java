package com.stockwaage.service.resources.weights;

import com.stockwaage.service.weights.Weight;

import java.util.List;
import java.util.stream.Collectors;

public class WeightAssembler {

  List<WeightRepresentation> assemble(List<Weight> weights) {
    return weights.stream().map(weight -> assemble(weight)).collect(Collectors.toList());
  }

  private WeightRepresentation assemble(Weight weight) {
    return new WeightRepresentation(weight.loadCellId(), weight.weightUnit(), weight
        .value(), weight.timestamp());
  }
}

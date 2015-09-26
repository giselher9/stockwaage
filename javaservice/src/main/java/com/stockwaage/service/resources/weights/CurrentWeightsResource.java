package com.stockwaage.service.resources.weights;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stockwaage.service.loadcell.HttpLoadCellConnector;
import com.stockwaage.service.weights.Weight;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class CurrentWeightsResource {

  private WeightAssembler weightAssembler;
  private HttpLoadCellConnector loadCellConnector;

  public CurrentWeightsResource(WeightAssembler weightAssembler,
                                HttpLoadCellConnector loadCellConnector) {
    this.weightAssembler = weightAssembler;
    this.loadCellConnector = loadCellConnector;
  }

  @GET
  @Path("/currentweights")
  @Produces(MediaType.APPLICATION_JSON)
  public List<WeightRepresentation> currentWeights(@QueryParam("loadCell")
                                                   String loadCell) throws JsonMappingException, JsonParseException, IOException {
    Weight weight = loadCellConnector.currentWeight();

    if (!Strings.isNullOrEmpty(loadCell) && !loadCell.equals(weight.loadCellId())) {
      return Lists.newArrayList();
    }
    return Lists.newArrayList(weightAssembler.assemble(weight));
  }

}
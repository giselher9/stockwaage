package com.stockwaage.service.resources.weights;

import com.google.common.base.Strings;

import com.stockwaage.service.weights.WeightDao;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class CurrentWeightsResource {

  private WeightAssembler weightAssembler;
  private WeightDao weightDao;

  public CurrentWeightsResource(WeightDao weightDao, WeightAssembler weightAssembler){
    this.weightAssembler = weightAssembler;
    this.weightDao = weightDao;
  }

  @GET
  @Path("/currentweights")
  @Produces(MediaType.APPLICATION_JSON)
  public List<WeightRepresentation> currentWeights(@QueryParam("loadCell")
                                                     String loadCell) {
    if(Strings.isNullOrEmpty(loadCell)) {
      return weightAssembler.assemble(weightDao.allCurrentWeights());
    };
    return weightAssembler.assemble(weightDao.currentWeightsBy(loadCell));
  }

}
package com.stockwaage.service.resources.weights;

import com.google.common.base.Strings;

import com.stockwaage.service.weights.WeightDao;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class HistoricWeightsResource {

  private WeightAssembler weightAssembler;
  private WeightDao weightDao;

  public HistoricWeightsResource(WeightDao weightDao, WeightAssembler weightAssembler) {
    this.weightAssembler = weightAssembler;
    this.weightDao = weightDao;
  }

  @GET
  @Path("/historicweights")
  @Produces(MediaType.APPLICATION_JSON)
  public List<WeightRepresentation> historicWeights(@QueryParam("loadCell")
                                                    String loadCell) throws SQLException, ClassNotFoundException {
    if (!Strings.isNullOrEmpty(loadCell)) {
      return weightAssembler.assemble(weightDao.allHistoricWeights());
    }
    return weightAssembler.assemble(weightDao.allHistoricWeights());
  }

}
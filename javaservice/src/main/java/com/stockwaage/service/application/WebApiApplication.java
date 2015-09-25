package com.stockwaage.service.application;

import com.stockwaage.service.config.StockwaageServiceConfiguration;
import com.stockwaage.service.resources.weights.CurrentWeightsResource;
import com.stockwaage.service.resources.weights.HistoricWeightsResource;
import com.stockwaage.service.resources.weights.WeightAssembler;
import com.stockwaage.service.weights.WeightDao;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebApiApplication extends Application<StockwaageServiceConfiguration> {

  public static void main(String[] args) throws Exception {
    new WebApiApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<StockwaageServiceConfiguration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/webapp/", "/frontend"));
  }

  @Override
  public void run(StockwaageServiceConfiguration configuration, Environment environment) {
    final WeightDao weightDao = new WeightDao();
    final WeightAssembler weightAssembler = new WeightAssembler();
    final CurrentWeightsResource currentWeightsResource = new CurrentWeightsResource(weightDao,
        weightAssembler);
    final HistoricWeightsResource historicWeightsResource = new HistoricWeightsResource
        (weightDao, weightAssembler);
    environment.jersey().register(currentWeightsResource);
    environment.jersey().register(historicWeightsResource);
  }
}

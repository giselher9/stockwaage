package com.stockwaage.service.application;

import com.stockwaage.service.config.StockwaageServiceConfiguration;
import com.stockwaage.service.db.SqliteJdbcClient;
import com.stockwaage.service.loadcell.HttpLoadCellConnector;
import com.stockwaage.service.loadcell.LoadCellSyncer;
import com.stockwaage.service.resources.weights.CurrentWeightsResource;
import com.stockwaage.service.resources.weights.HistoricWeightsResource;
import com.stockwaage.service.resources.weights.WeightAssembler;
import com.stockwaage.service.weights.WeightDao;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

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
    final SqliteJdbcClient jdbcClient = new SqliteJdbcClient();
    final HttpLoadCellConnector loadCellConnector = new HttpLoadCellConnector();
    addCorsServletFilter(environment);
    startLoadCellSyncer(jdbcClient, loadCellConnector);
    registerResources(environment, jdbcClient, loadCellConnector);
  }

  private void startLoadCellSyncer(SqliteJdbcClient jdbcClient, HttpLoadCellConnector loadCellConnector) {
    final LoadCellSyncer loadCellSyncer = new LoadCellSyncer(loadCellConnector, jdbcClient);
    loadCellSyncer.start();
  }

  private void registerResources(Environment environment, SqliteJdbcClient jdbcClient, HttpLoadCellConnector loadCellConnector) {
    final WeightDao weightDao = new WeightDao(jdbcClient);
    final WeightAssembler weightAssembler = new WeightAssembler();

    final CurrentWeightsResource currentWeightsResource = new CurrentWeightsResource(
        weightAssembler, loadCellConnector);
    final HistoricWeightsResource historicWeightsResource = new HistoricWeightsResource
        (weightDao, weightAssembler);
    environment.jersey().register(currentWeightsResource);
    environment.jersey().register(historicWeightsResource);
  }

  private void addCorsServletFilter(Environment environment) {
    final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
    cors.setInitParameter("allowedOrigins", "*");
    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
  }
}

package com.stockwaage.service.application;

import com.stockwaage.service.config.StockwaageServiceConfiguration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;

public class WebApiApplication extends Application<StockwaageServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new WebApiApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<StockwaageServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/webapp/", "/x"));
    }

    @Override
    public void run(StockwaageServiceConfiguration configuration, Environment environment) {

    }
}

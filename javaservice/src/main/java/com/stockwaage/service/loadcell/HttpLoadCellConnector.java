package com.stockwaage.service.loadcell;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockwaage.service.weights.Weight;
import com.stockwaage.service.weights.WeightUnit;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.soap.AddressingFeature;

public class HttpLoadCellConnector {

  private WebTarget loadCell;

  public HttpLoadCellConnector() {

    loadCell = ClientBuilder
        .newClient()
        .target("http://53.48.8.178")
        .path("/arduino/api/weight");
  }


  public Weight currentWeight(){
    Response response = loadCell
        .request(MediaType.APPLICATION_JSON)
        .get();

    String json = response.readEntity(String.class);
    ObjectMapper mapper = new ObjectMapper();
    try {
      LoadCellWeight weight = mapper.readValue(json, LoadCellWeight.class);
      return new Weight(weight.sensor(), WeightUnit.fromString(weight.unit()), weight.value(),
          LocalDateTime
          .now());
    }catch(JsonMappingException e){
      //todo
    } catch(JsonParseException e){
      //todo
    } catch (IOException e){
      //todo
    }
    return null;
  }

}

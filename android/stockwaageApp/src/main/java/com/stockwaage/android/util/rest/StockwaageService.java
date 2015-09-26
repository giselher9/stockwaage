package com.stockwaage.android.util.rest;

import java.util.List;

import retrofit.http.GET;

import com.stockwaage.android.dto.WeightDto;

public interface StockwaageService {

    // @PUT("/person/{emeaId}/token")
    // Response registerToken(@Path("emeaId") String emeaId, @Body TokenDto tokenDto);
    //
    // @POST("/person/new")
    // Response registerNewUser(@Body PersonDto personDto);
    //
    // @PUT("/person/{emeaId}/update")
    // Response updateUser(@Path("emeaId") String emeaId, @Body PersonDto personDto);
    //
    // @GET("/person/{emeaId}")
    // PersonDto getUser(@Path("emeaId") String emeaId);
    //
    // @GET("/notification/get")
    // List<NotificationDto> getNotifications();

    @GET("/currentweights/")
    WeightDto getWeight();
}

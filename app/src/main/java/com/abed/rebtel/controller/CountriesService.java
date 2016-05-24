package com.abed.rebtel.controller;

import com.abed.rebtel.model.Capital;
import com.abed.rebtel.model.Country;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Retrofit Service.
 * Here we list all the apis related to the countries.
 */
public interface CountriesService {
    @GET("/rest/v1/all")
    Observable<List<Country>> getCountries();

    @GET("/rest/v1/capital/{capital}")
    Observable<Capital> getCapitalInfo(@Path("capital") String capital);


}


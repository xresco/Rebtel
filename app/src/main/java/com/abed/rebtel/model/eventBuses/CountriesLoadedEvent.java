package com.abed.rebtel.model.eventBuses;

import com.abed.rebtel.model.Country;

import java.util.List;


public class CountriesLoadedEvent {

    private List<Country> countries;

    public CountriesLoadedEvent(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }
}

package com.abed.rebtel.model.eventBuses;

import com.abed.rebtel.model.Capital;

import java.util.List;


public class CapitalLoadedEvent {

    private List<Capital> capitals;

    public CapitalLoadedEvent(List<Capital> cmnts) {
        capitals = cmnts;
    }

    public List<Capital> getCapitals() {
        return capitals;
    }
}

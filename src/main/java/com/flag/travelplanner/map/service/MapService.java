package com.flag.travelplanner.map.service;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.route.entity.Route;
import com.google.maps.model.LatLng;

import java.util.List;

public interface MapService {
    LatLng getLatLngFromAddress(String address);
    String getAddressFromLatLng();
    double [][] buildDistanceMatrix(Route route);
    double [] getDistanceFromSource(Route route);

    //List<POI> searchNearbyPlances()
}

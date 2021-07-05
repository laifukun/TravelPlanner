package com.flag.travelplanner.map.service;

import com.flag.travelplanner.map.entity.Point;
import com.flag.travelplanner.route.entity.Route;

public interface MapService {
    Point getLatLngFromAddress(String address);
    String getAdressFromLatLng(Point point);
    double [][] buildDistanceMatrix(Route route);
    double [] getDistanceFromSource(Route route);
}

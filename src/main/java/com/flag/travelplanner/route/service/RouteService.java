package com.flag.travelplanner.route.service;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.route.entity.Route;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public interface RouteService {

    List<POI> retrieveRoute(long id);
    void updateRoute(Route route);
    void deleteRoute(long id);
    void saveRoute(Route route);
}

// RouteService interface to be implemented
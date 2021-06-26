package com.flag.travelplanner.route.service;

import com.flag.travelplanner.route.entity.Route;


import java.util.ArrayList;
import java.util.List;

public interface RouteService {

    Route retrieveRoute(long id);
    void updateRoute(Route route);
    void deleteRoute(long id);
    Route saveRoute(Route route);
}

// RouteService interface to be implemented
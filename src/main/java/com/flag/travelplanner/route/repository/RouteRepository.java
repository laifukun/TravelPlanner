package com.flag.travelplanner.route.repository;
import com.flag.travelplanner.route.entity.Route;

public interface RouteRepository {
    long count();
    Route save(Route route);
    Route findById(long id);
    int update(Route route);
    int deleteById(long id);
}

package com.flag.travelplanner.route.repository;


import com.flag.travelplanner.route.entity.Route;

public interface RouteRepository {

    public long count();
    public int save(Route route);
    public Route findById(long id);
    public int update(Route route);
    public int deleteById(long id);
}

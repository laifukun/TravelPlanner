package com.flag.travelplanner.route.service;

import com.flag.travelplanner.route.entity.Route;
import com.flag.travelplanner.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;
    @Override
    public Route retrieveRoute(long id) {
        return routeRepository.findById(id);
    }

    @Override
    public void updateRoute(Route route) {
        routeRepository.update(route);
    }

    @Override
    public void deleteRoute(long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }
}

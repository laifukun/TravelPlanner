package com.flag.travelplanner.route.service;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.route.entity.POIRoute;
import com.flag.travelplanner.route.entity.Route;
import com.flag.travelplanner.route.repository.POIRouteRepository;
import com.flag.travelplanner.route.repository.RouteRepository;
import com.flag.travelplanner.user.entity.User;
import com.flag.travelplanner.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private POIRouteRepository poiRouteRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Route retrieveRouteDetails(long id) {
        Route route = routeRepository.findById(id);
        List<POI> poiList = poiRouteRepository.findByRoute(id);
        route.setPoiList(poiList);
        return route;
    }

    @Override
    @Transactional
    public List<Route> retrieveAllRoutesOfUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //User user = userService.getUserByUserName(username);

        return routeRepository.findRoutesByUser(username);
    }

    @Override
    @Transactional
    public void updateRoute(Route route) {
        List<POIRoute> poiRouteList = new LinkedList<>();
        for (POI poi : route.getPoiList()) {
            poiRouteList.add(new POIRoute(poi.getPoiId(), route.getRouteId()));
        }
        poiRouteRepository.deleteByRoute(route.getRouteId());
        routeRepository.update(route);
        poiRouteRepository.save(poiRouteList);
    }

    @Override
    public void deleteRoute(long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public Route saveRoute(Route route) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUserName(username);
        route.setUser(user);
        Route newRoute = routeRepository.save(route);
        List<POIRoute> poiRouteList = new LinkedList<>();
        for (POI poi : route.getPoiList()) {
            poiRouteList.add(new POIRoute(poi.getPoiId(), newRoute.getRouteId()));
        }
        poiRouteRepository.save(poiRouteList);

        return newRoute;
    }

    @Override
    public void deleteAllRoutesFromPlan(long planId) {
        routeRepository.deleteByPlan(planId);
    }

    @Override
    @Transactional
    public List<Route> retrieveAllRoutesOfPlan(long planId) {
        List<Route> routes = routeRepository.findRoutesByPlan(planId);
        for (Route route : routes) {
            List<POI> poiList = poiRouteRepository.findByRoute(route.getRouteId());
            route.setPoiList(poiList);
        }
        return routes;
    }
}

package com.flag.travelplanner.map.service;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.route.entity.Route;

import com.google.maps.DistanceMatrixApi;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;


import java.io.IOException;


@Service
public class MapServiceImpl implements MapService {
    private static String BASE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?";
    private static String API_KEY = "AIzaSyDtVxnLGqwly8qiErGo1wKya8yKYri6GIY";

    @Override
    public LatLng getLatLngFromAddress(String address) {
        return null;
    }

    @Override
    public String getAddressFromLatLng() {
        return null;
    }

    @Override
    public double[][] buildDistanceMatrix(Route route) {
        if (route == null) return null;
        String startAddress = route.getStartAddress();
        String endAddress = route.getEndAddress();
        int n = route.getPoiList().size();
        if (startAddress.equals(endAddress) || endAddress.trim().isEmpty()) {
            n += 1;
        } else {
            n += 2;
        }

        double [][] time = new double [n][n];

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        LatLng[] latLngs = new LatLng[n];
        int k = 1;
        for (POI poi : route.getPoiList()) {
            latLngs[k++] = new LatLng(poi.getLat(), poi.getLng());
        }


        GeocodingResult[] results;
        DistanceMatrix distanceMatrix = null;
        try {
            results = GeocodingApi.geocode(context,
                    startAddress).await();
            latLngs[0] = results[0].geometry.location;
            if (!startAddress.equals(endAddress) && !endAddress.trim().isEmpty()) {
                results = GeocodingApi.geocode(context,
                         endAddress).await();
                latLngs[n-1] = results[0].geometry.location;
            }
            int maxRows = 100/n;
            for (int p = 0; p < n; p += maxRows) {

                LatLng[] tmpOrigin = new LatLng[p + maxRows < n ? maxRows : n - p];
                for (int i = p; i < n && i < p + maxRows; i++) {
                    tmpOrigin[i - p] = latLngs[i];
                }

                distanceMatrix = DistanceMatrixApi.newRequest(context)
                        .origins(tmpOrigin)
                        .destinations(latLngs)
                        .await();
                for (int i = p; i < n && i < p + maxRows; i++)
                    for (int j = 0; j < n; j++) {
                        time[i][j] = distanceMatrix.rows[i - p].elements[j].duration.inSeconds / 3600.0;
                    }
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < n; i++) {
            int j = 1;
            for (POI poi : route.getPoiList()) {
                time[i][j++] += poi.getEstimateVisitTime();
            }
        }

        context.shutdown();
        return time;
    }

    @Override
    public double[] getDistanceFromSource(Route route) {
        if (route == null) return null;
        int n = route.getPoiList().size();
        //int n=2;
        double [] time = new double [n];

        String startAddress = route.getStartAddress();
        String endAddress = route.getStartAddress();

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        LatLng[] latLngs = new LatLng[n];
        int k = 0;
        for (POI poi : route.getPoiList()) {
            latLngs[k++] = new LatLng(poi.getLat(), poi.getLng());
        }

        DistanceMatrix distanceMatrix = null;
        try {
            distanceMatrix = DistanceMatrixApi.newRequest(context)
                    .origins(startAddress)
                    .destinations(latLngs)
                    .await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int j = 0; j < n; j++) {
            time[j] = distanceMatrix.rows[0].elements[j].duration.inSeconds/3600.0 ;
        }
        context.shutdown();
        return time;
    }

    public static void main(String[] args) {
        //MapService mapService = new MapServiceImpl();
        //RouteRepository routeRepository = new RouteRepositoryJdbc();
        //RouteService routeService = new RouteServiceImpl();
        //Route route = routeService.retrieveRouteDetails(5);
        //mapService.buildDistanceMatrix(null);
    }
}

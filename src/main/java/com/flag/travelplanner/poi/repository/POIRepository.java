package com.flag.travelplanner.poi.repository;

import com.flag.travelplanner.poi.entity.POI;

public interface POIRepository {
    public long count();
    public int create(POI poi);
    public POI findById(long id);
    public int update(POI poi);
    public int deleteById(long id);
}

package com.flag.travelplanner.route.entity;

import com.flag.travelplanner.poi.entity.POI;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route implements Serializable {

    private static final long serialVersionUID = 670983485351766613L;
    @Id
    private long routeId;

   // @ManyToMany
   // private List<POI> poiList;
}

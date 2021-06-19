package com.flag.travelplanner.poi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="pois")
public class POI implements Serializable {

    private static final long serialVersionUID = 263384195550410516L;
    @Id
    private long poiId;
}

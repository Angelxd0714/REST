package com.main.rest.interfaces;

import com.main.rest.entities.Marker;

public interface CrudMarker {
    public void create(Marker marker);
    public Marker read(Long id);
    public void update(Marker marker, Long id);
    public void delete(Long id);
    public Iterable<Marker> getAll();
}

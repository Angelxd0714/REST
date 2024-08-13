package com.main.rest.services;

import com.main.rest.entities.Marker;
import com.main.rest.interfaces.CrudMarker;
import com.main.rest.repository.RepositoryMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesMarker implements CrudMarker {
    @Autowired
    private RepositoryMarker repositoryMarker;
    @Override
    public void create(Marker marker) {
            repositoryMarker.save(marker);
    }

    @Override
    public Marker read(Long id) {
        return repositoryMarker.findById(id).orElse(null);
    }

    @Override
    public void update(Marker marker, Long id) {
             Marker marker1 = repositoryMarker.findById(id).orElse(null);
             marker1.setName(marker.getName());
             repositoryMarker.save(marker1);
    }

    @Override
    public void delete(Long id) {
              repositoryMarker.deleteById(id);
    }

    @Override
    public Iterable<Marker> getAll() {
        return repositoryMarker.findAll();
    }
}

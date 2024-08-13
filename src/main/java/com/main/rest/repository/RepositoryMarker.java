package com.main.rest.repository;

import com.main.rest.entities.Marker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMarker extends CrudRepository<Marker, Long>{
}

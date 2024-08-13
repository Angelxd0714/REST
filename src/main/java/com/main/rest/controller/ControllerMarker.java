package com.main.rest.controller;

import com.main.rest.entities.Marker;
import com.main.rest.services.ServicesMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/marker")
public class ControllerMarker {
    @Autowired
    private ServicesMarker serviceMarker;

    @GetMapping("/markerAll")
    public ResponseEntity<?> markerAll() {
        try {
                return ResponseEntity.ok(serviceMarker.getAll());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/markerById/{id}")
    public ResponseEntity<?> markerById(@PathVariable Long id) {
        try {
                return ResponseEntity.ok(serviceMarker.read(id));
        }catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/markerCreate")
        public ResponseEntity<?> markerCreate(@RequestBody Marker marker) {
        try {
            serviceMarker.create(marker);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/markerUpdate/{id}")
    public ResponseEntity<?> markerUpdate(@RequestBody Marker marker,@PathVariable Long id) {
        try {
            serviceMarker.update(marker,id);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/markerDelete/{id}")
    public ResponseEntity<?> markerDelete(@PathVariable Long id) {
        try {
            serviceMarker.delete(id);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.example.seminar8springAOP.controllers;

import com.example.seminar8springAOP.models.Performer;
import com.example.seminar8springAOP.services.My_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performers")
public class PerformerController {
    private final My_Service service;

    @Autowired
    public PerformerController(My_Service service1) {
        service = service1;
    }

    @GetMapping
    public List<String> getAllPerformers() {
        return service.getAllPerformers();
    }

    @GetMapping("/{id}")
    public String getPerformerById(@PathVariable Long id) {
        return service.getPerformerById(id).toString();
    }
    @GetMapping("/working")
    public List<String> getPerformerByStatus(){
        return service.getPerformerByStatus();
    }

    @PostMapping
    public String createPerformer(@RequestBody Performer note) {
        return service.createPerformer(note).toString();
    }

    @DeleteMapping("/{id}")
    public void deletePerformer(@PathVariable Long id) {
        service.deletePerformer(id);
    }
}

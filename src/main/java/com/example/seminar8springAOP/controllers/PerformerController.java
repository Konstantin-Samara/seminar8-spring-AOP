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

    @GetMapping("/user")
    public List<String> getAllPerformers() {
        return service.getAllPerformers();
    }

    @GetMapping("/user/{id}")
    public String getPerformerById(@PathVariable Long id) {
        return service.getPerformerById(id).toString();
    }
    @GetMapping("/user/working")
    public List<String> getPerformerByStatus(){
        return service.getPerformerByStatus();
    }

    @GetMapping("/admin/create")
    public String createPerformer(@RequestBody Performer note) {
        return service.createPerformer(note).toString();
    }

    @GetMapping("/admin/delete/{id}")
    public void deletePerformer(@PathVariable Long id) {
        service.deletePerformer(id);
    }
}

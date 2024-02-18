package com.example.seminar8springAOP.controllers;

import com.example.seminar8springAOP.models.Command;
import com.example.seminar8springAOP.services.My_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {
    private final My_Service service;

    @Autowired
    public CommandController(My_Service service1) {
        service = service1;
    }

    @GetMapping
    public List<String> getAllCommands() {
        return service.getAllCommands();
    }

    @GetMapping("/{id}")
    public String getCommandById(@PathVariable Long id) {
        return service.getCommandById(id).toString();
    }
    @GetMapping("/running")
    public List<String> getCmmandByStatus(){
        return service.getCommandByStatus();
    }

    @PostMapping
    public String createCommand(@RequestBody Command note) {
        return service.createCommand(note).toString();
    }

    @PutMapping("/{id}")
    public String changeCommandStatus(@PathVariable Long id) {
        return service.changeCommandStatus(id).toString();
    }

    @DeleteMapping("/{id}")
    public void deleteCommand(@PathVariable Long id) {
        service.deleteCommand(id);
    }
    @PutMapping("/{id}/change/{id1}")
    public String addPerformer(@PathVariable Long id, @PathVariable Long id1) {

        return service.changeCommandPerformer(id,id1).toString();

    }



}

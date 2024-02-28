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

    @GetMapping("/user")
    public List<String> getAllCommands() {
        return service.getAllCommands();
    }

    @GetMapping("/user/{id}")
    public String getCommandById(@PathVariable Long id) {
        return service.getCommandById(id).toString();
    }

    @GetMapping("/user/running")
    public List<String> getCmmandByStatus(){
        return service.getCommandByStatus();
    }

    @GetMapping("/admin")
    public String createCommand(@RequestBody Command note) {
        return service.createCommand(note).toString();
    }

    @GetMapping("/admin/change/{id}")
    public String changeCommandStatus(@PathVariable Long id) {
        return service.changeCommandStatus(id).toString();
    }

    @GetMapping("/admin/delete/{id}")
    public void deleteCommand(@PathVariable Long id) {
        service.deleteCommand(id);
    }

    @GetMapping("/admin/{id}/change/{id1}")
    public String changeCommandPerformer(@PathVariable Long id, @PathVariable Long id1) {

        return service.changeCommandPerformer(id,id1).toString();

    }



}

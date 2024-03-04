package com.example.seminar8springAOP.controllers;

import com.example.seminar8springAOP.models.Command;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/commands")
public interface CommandController {

    @GetMapping("/user")
    public List<String> getAllCommands() ;

    @GetMapping("/user/{id}")
    public String getCommandById(@PathVariable Long id) ;

    @GetMapping("/user/running")
    public List<String> getCmmandByStatus();

    @GetMapping("/admin")
    public String createCommand(@RequestBody Command note) ;

    @GetMapping("/admin/change/{id}")
    public String changeCommandStatus(@PathVariable Long id) ;

    @GetMapping("/admin/delete/{id}")
    public void deleteCommand(@PathVariable Long id) ;

    @GetMapping("/admin/{id}/change/{id1}")
    public String changeCommandPerformer(@PathVariable Long id, @PathVariable Long id1) ;

    @GetMapping("/admin/complete")
    public List<String> complete() ;

}

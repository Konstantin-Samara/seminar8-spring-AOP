package com.example.seminar8springAOP.controllers;

import com.example.seminar8springAOP.models.Performer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/performers")
public interface PerformerController {

    @GetMapping("/user")
    public List<String> getAllPerformers();

    @GetMapping("/user/{id}")
    public String getPerformerById(@PathVariable Long id);

    @GetMapping("/user/working")
    public List<String> getPerformerByStatus();

    @GetMapping("/admin/create")
    public String createPerformer(@RequestBody Performer note);

    @GetMapping("/admin/delete/{id}")
    public void deletePerformer(@PathVariable Long id);

    @GetMapping("/admin/complete")
    public List<String> complete();
}

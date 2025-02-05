package com.devansh.jwt.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @GetMapping
    public String get() {
        return "GET: users controller bro";
    }

    @PostMapping
    public String post() {
        return "POST: users controller bro";
    }

    @PutMapping
    public String put() {
        return "PUT: user controller bro";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE: users controller bro";
    }
}

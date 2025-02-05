package com.devansh.jwt.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String get() {
        return "GET: admin controller bro";
    }

    @PostMapping
    public String post() {
        return "POST: admin controller bro";
    }

    @PutMapping
    public String put() {
        return "PUT: admin controller bro";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE: admin controller bro";
    }
}

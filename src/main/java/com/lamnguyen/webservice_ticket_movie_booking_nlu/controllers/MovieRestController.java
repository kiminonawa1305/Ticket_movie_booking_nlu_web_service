package com.lamnguyen.webservice_ticket_movie_booking_nlu.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/movie/api")
public class MovieRestController {
    @GetMapping(value = "/get")
    public String get() {
        return "GET";
    }

    @PostMapping(value = "/post")
    public String post(@RequestParam Map<String, Object> attr) {
        return "POST";
    }

    @DeleteMapping(value = "/delete")
    public String delete() {
        return "DELETE";
    }

    @PutMapping(value = "/put")
    public String put() {
        return "PUT";
    }

    @PatchMapping(value = "/patch")
    public String patch() {
        return "patch";
    }
}


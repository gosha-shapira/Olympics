package com.handson.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/athletes")
public class AthletesController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> helloWorld()
    {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
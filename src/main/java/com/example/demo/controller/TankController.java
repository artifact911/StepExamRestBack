package com.example.demo.controller;

import com.example.demo.model.Tank;
import com.example.demo.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tanks")
public class TankController {

    private final TankService service;

    @Autowired
    public TankController(TankService service) {
        this.service = service;
    }


    @GetMapping("/list")
    public ResponseEntity<List<Tank>> getTanks() {
        List<Tank> tanks = service.getAllTanks();
        return ResponseEntity.ok(tanks);
    }

    @GetMapping("/create")
    public ResponseEntity<List<Tank>> createTanks(@RequestParam("amount") int amount){
        return ResponseEntity.ok(service.createTanks(amount));
    }




}

package com.example.demo.controller;

import com.example.demo.model.Tank;
import com.example.demo.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tanks")
public class TankController {

    private final TankService service;

    @Autowired
    public TankController(TankService service) {
        this.service = service;
    }


    @GetMapping("/list")
    public ResponseEntity<?> getTanks() {
        try {
            List<Tank> tanks = service.getAllTanks();
            return ResponseEntity.ok(tanks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/create")
    public ResponseEntity<?> createTanks(@RequestParam("amount") int amount) {
        try {
            return ResponseEntity.ok(service.createTanks(amount));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/addWater")
    public ResponseEntity<?> addWater(@RequestParam("waterAmount") int waterAmount, @RequestParam("id") long id){
        try {
            return ResponseEntity.ok(service.addWater(id, waterAmount));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/connectTo")
    public ResponseEntity<?> connectTo(@RequestParam("otherId") long otherId, @RequestParam("id") long id){
        try {
            return ResponseEntity.ok(service.connectTo(id, otherId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_GATEWAY);
    }


}

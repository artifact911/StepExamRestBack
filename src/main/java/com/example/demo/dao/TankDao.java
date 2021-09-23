package com.example.demo.dao;

import com.example.demo.model.Tank;

import java.util.List;

public interface TankDao {

    void addTanks(List<Tank> tanks);

    List<Tank> getTanks();
}

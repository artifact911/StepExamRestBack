package com.example.demo.dao;

import com.example.demo.model.Tank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TankDaoImpl implements TankDao {

    private static List<Tank> tanks = new ArrayList<>();


    @Override
    public void addTanks(List<Tank> tankes) {
        tanks.addAll(tankes);
    }

    @Override
    public List<Tank> getTanks() {
        return tanks;
    }
}

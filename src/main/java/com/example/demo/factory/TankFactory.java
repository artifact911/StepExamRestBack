package com.example.demo.factory;

import com.example.demo.model.Tank;

import java.util.ArrayList;
import java.util.List;

public abstract class TankFactory {

    private static List<Tank> tankList = new ArrayList<>();


    public static List<Tank> getNewTankList(int amount) {
        for (int i = 0; i < amount; i++) {
            tankList.add(new Tank());
        }
        return tankList;
    }
}

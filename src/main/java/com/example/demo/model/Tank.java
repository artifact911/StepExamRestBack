package com.example.demo.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Tank {

    private static AtomicLong atomicLong = new AtomicLong();
    private long id = atomicLong.incrementAndGet();
    private double waterAmount;

//    private Set<Tank> foreignTanks = new HashSet<>();
    private Set<Long> foreignTanks = new HashSet<>();

    public Tank() {
        foreignTanks.add(this.id);
    }

    public double getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(double waterAmount) {
        this.waterAmount = waterAmount;

    }

    public long getId() {
        return id;
    }

    public Set<Long> getForeignTanks() {
        return foreignTanks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tank tank = (Tank) o;
        return id == tank.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

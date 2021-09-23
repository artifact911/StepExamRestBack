package com.example.demo.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Tank {

    private static AtomicLong atomicLong = new AtomicLong();
    private long id = atomicLong.incrementAndGet();
    private double capacity;

    private Set<Tank> foreignTanks = new HashSet<>();

    public Tank() {
        foreignTanks.add(this);
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;

    }

    public long getId() {
        return id;
    }

    public Set<Tank> getForeignTanks() {
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

package com.example.demo.service;

import com.example.demo.exception.MyException;
import com.example.demo.model.Tank;

import java.util.List;

public interface TankService {

    Tank getTank(long id) throws MyException;

    List<Tank> getAllTanks();

    List<Tank> createTanks(int amount);

    List<Tank> addWater(long id, int waterAmount) throws MyException;

    List<Tank> connectTo(long id, long otherId) throws MyException;

}

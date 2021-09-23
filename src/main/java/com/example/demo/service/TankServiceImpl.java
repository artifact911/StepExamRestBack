package com.example.demo.service;

import com.example.demo.dao.TankDao;
import com.example.demo.exception.MyException;
import com.example.demo.factory.TankFactory;
import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TankServiceImpl implements TankService {

    private final TankDao dao;

    @Autowired
    public TankServiceImpl(TankDao dao) {
        this.dao = dao;
    }

    @Override
    public Tank getTank(long id) throws MyException {

        for (Tank value : dao.getTanks()) {
            if (value.getId() == id) {
                return value;
            }
        }
        return new Tank();

//        final Optional<Tank> optionalTank = dao.getTanks().stream().filter(tank -> id == tank.getId()).findAny();
//
//        return optionalTank.orElseThrow(() -> new MyException("not found"));
    }

    @Override
    public List<Tank> getAllTanks() {
        return dao.getTanks();
    }

    @Override
    public List<Tank> createTanks(int amount) {
        for (int i = 0; i < amount; i++) {
            getAllTanks().add(new Tank());
        }
        return dao.getTanks();
    }

    @Override
    public List<Tank> addWater(long id, int waterAmount) throws MyException {
        getTank(id).setCapacity(waterAmount);
        divideWater(getTank(id).getForeignTanks());
        return getAllTanks();
    }

    @Override
    public List<Tank> connectTo(long id, long otherId) throws MyException {

        final Tank sourceTank = getTank(id);
        final Tank connectingTank = getTank(otherId);

        sourceTank.getForeignTanks().addAll(connectingTank.getForeignTanks());

        Set<Tank> setOfForeignTanks = new HashSet<>(sourceTank.getForeignTanks());

        for (Tank value : sourceTank.getForeignTanks()) {

            value.getForeignTanks().addAll(setOfForeignTanks);
        }
        return getAllTanks();
    }

    private void divideWater(Set<Tank> tanks) {
        final double sum = tanks.stream().mapToDouble(Tank::getCapacity).sum();

        for (Tank tank : tanks) {
            tank.setCapacity(sum / tanks.size());
        }
    }


}
